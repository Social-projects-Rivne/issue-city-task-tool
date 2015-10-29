package edu.com.softserveinc.bawl.dto;

import edu.com.softserveinc.bawl.models.CategoryModel;
import edu.com.softserveinc.bawl.models.HistoryModel;
import edu.com.softserveinc.bawl.models.IssueModel;
import edu.com.softserveinc.bawl.models.enums.IssueStatus;
import edu.com.softserveinc.bawl.models.UserModel;
import edu.com.softserveinc.bawl.services.UserService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Utility class for mapping domain models into DTOs consumed by REST controllers
 * @see 'http://martinfowler.com/eaaCatalog/dataTransferObject.html'
 */
public class DTOAssembler {

    public static UserDTO getUserDtoFrom(UserModel userModel) {
        UserDTO userDTO =  new UserDTO();
        userDTO.setId(userModel.getId());
        userDTO.setName(userModel.getName());
        userDTO.setEmail(userModel.getEmail());
        userDTO.setLogin(userModel.getLogin());
        userDTO.setAvatar(userModel.getAvatar());
        userDTO.setRoleId(String.valueOf(userModel.getRole().id));
        return userDTO;
    }

    public static List<StatusDTO> getStatusDtos() {
        List<StatusDTO> categoryDTOs = new ArrayList<>();
        for (IssueStatus status : IssueStatus.values()) {
            StatusDTO statusDTO = new StatusDTO();
            statusDTO.setId(status.id);
            statusDTO.setName(status.name());
            categoryDTOs.add(statusDTO);
        }
        return categoryDTOs;
    }

    public static List<CategoryDTO> getCategoryDtoFrom(List<CategoryModel> categoryModels, boolean mapIssues) {
        List<CategoryDTO> categoryDTOs = new ArrayList<>();
        categoryModels.forEach(categoryModel -> {
            categoryDTOs.add(getCategoryDtoFrom(categoryModel, mapIssues));
        });
        return categoryDTOs;
    }

    public static CategoryDTO getCategoryDtoFrom(CategoryModel categoryModel, boolean mapIssues) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(categoryModel.getId());
        categoryDTO.setName(categoryModel.getName());
        categoryDTO.setState(categoryModel.getState().ordinal());
        if (mapIssues) {
            categoryDTO.setIssueDtoList(getAllIssuesDto(categoryModel.getIssues()));
        }
        return  categoryDTO;
    }
    /**
     * Returns all issues
     * @param allIssues
     * @return
     */
    public static List<IssueDto> getAllIssuesDto(List<IssueModel> allIssues){
        List<IssueDto> listIssueDto = new ArrayList<>(allIssues.size());
        allIssues.forEach(issueModel -> {
                    if (issueModel.getStatus() != IssueStatus.RESOLVED) {
                        listIssueDto.add(getIssueDto(issueModel));
                    }
                }
        );
        return listIssueDto;
    }

    public static IssueDto getIssueDto(IssueModel issueModel) {
        IssueDto issueDto = new IssueDto();
        issueDto.setId(issueModel.getId());
        issueDto.setName(issueModel.getName());
        issueDto.setDescription(issueModel.getDescription());
        issueDto.setAttachments(issueModel.getAttachments());
        issueDto.setMapPointer(issueModel.getMapPointer());
        issueDto.setCategoryId(issueModel.getCategory().getId());
        issueDto.setPriorityId(issueModel.getPriorityId());
        issueDto.setStatusId(issueModel.getStatus().id);
        return issueDto;
    }

    public static List<UserIssuesHistoryDto> getAllUserIssuesHistoryDTO(List<HistoryModel> listOfHistoriesByUserID, List<IssueModel> issues, UserModel userModel) {
        List<UserIssuesHistoryDto> list = new ArrayList<UserIssuesHistoryDto>();
        listOfHistoriesByUserID.forEach(historyModel -> {
            issues.forEach(issueModel -> {
                if (issueModel.getId() == historyModel.getIssue().getId()) {
                    list.add(getSingleUserIssuesHistoryDTO(historyModel, issueModel, userModel));
                }
            });
        });
        return list;
    }

    public static UserIssuesHistoryDto getSingleUserIssuesHistoryDTO(HistoryModel historyModel, IssueModel issueModel, UserModel userModel) {

        UserIssuesHistoryDto userIssuesHistoryDto = new UserIssuesHistoryDto();
        userIssuesHistoryDto.setIssueName(issueModel.getName());
        IssueHistoryDto issueHistoryDto = new IssueHistoryDto();
        issueHistoryDto.setDate(historyModel.getDate());
        issueHistoryDto.setChangedByUser(userModel.getName());
        issueHistoryDto.setStatus(historyModel.getStatus().id);
        userIssuesHistoryDto.setIssueHistoryDto(issueHistoryDto);
        userIssuesHistoryDto.setCurrentStatus(issueModel.getStatus().id);

        return userIssuesHistoryDto;
    }


    public static List<UserHistoryDto> getUserHistoryDtos(IssueModel issue, UserService userService) {
        List<UserHistoryDto> historyDtoList = new ArrayList<UserHistoryDto>();
        if (null == issue) {
            return historyDtoList;
        }
        for(HistoryModel historyModel : issue.getHistories()){
              UserHistoryDto userHistoryDto = getUserHistoryDto(historyModel, issue, userService);
              historyDtoList.add(userHistoryDto);
        }
        Collections.sort(historyDtoList);
        return historyDtoList;
    }

    public static UserHistoryDto getUserHistoryDto(HistoryModel historyModel, IssueModel issueModel, UserService userService){
        UserHistoryDto userHistoryDto = new UserHistoryDto();
        userHistoryDto.setStatusId(historyModel.getStatus().id);
        userHistoryDto.setDate(historyModel.getDate());
        userHistoryDto.setIssueName(issueModel.getName());
        UserModel userModel = userService.getById(historyModel.getUserId());
        userHistoryDto.setRoleName(userModel.getRole().get());
        userHistoryDto.setUsername(userModel.getName());
        return userHistoryDto;
    }


}
