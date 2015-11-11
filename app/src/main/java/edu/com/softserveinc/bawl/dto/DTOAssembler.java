package edu.com.softserveinc.bawl.dto;

import edu.com.softserveinc.bawl.models.CategoryModel;
import edu.com.softserveinc.bawl.models.CommentModel;
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

    public static List<UserDTO> getAllUsersDtoFrom(List<UserModel> userModels) {
        List<UserDTO> userDTOs = new ArrayList<>();
        userModels.forEach(userModel -> {
            userDTOs.add(getUserDtoFrom(userModel));
        });
        return userDTOs;
    }

    public static UserDTO getUserDtoFrom(UserModel userModel) {
        UserDTO userDTO =  new UserDTO();
        userDTO.setId(userModel.getId());
        userDTO.setName(userModel.getName());
        userDTO.setEmail(userModel.getEmail());
        userDTO.setLogin(userModel.getLogin());
        userDTO.setAvatar(userModel.getAvatar());
        userDTO.setRoleId(userModel.getRole().id);
        return userDTO;
    }

    public static List<StatusDTO> getStatusDtos() {
        List<StatusDTO> categoryDTOs = new ArrayList<>();
        for (IssueStatus status : IssueStatus.values()) {
            StatusDTO statusDTO = new StatusDTO();
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
    public static List<IssueDTO> getAllIssuesDto(List<IssueModel> allIssues){
        List<IssueDTO> listIssueDTO = new ArrayList<>(allIssues.size());
        allIssues.forEach(issueModel -> {
                    if (issueModel.getStatus() != IssueStatus.RESOLVED) {
                        listIssueDTO.add(getIssueDto(issueModel));
                    }
                }
        );
        return listIssueDTO;
    }

    public static IssueDTO getIssueDto(IssueModel issueModel) {
        IssueDTO issueDTO = new IssueDTO();
        issueDTO.setId(issueModel.getId());
        issueDTO.setName(issueModel.getName());
        issueDTO.setDescription(issueModel.getDescription());
        issueDTO.setAttachments(issueModel.getAttachments());
        issueDTO.setMapPointer(issueModel.getMapPointer());
        issueDTO.setCategoryId(issueModel.getCategory().getId());
        issueDTO.setPriorityId(issueModel.getPriorityId());
        issueDTO.setStatus(issueModel.getStatus().name());
        return issueDTO;
    }

    public static List<UserIssuesHistoryDTO> getAllUserIssuesHistoryDTO(List<HistoryModel> listOfHistoriesByUserID, List<IssueModel> issues, UserModel userModel) {
        List<UserIssuesHistoryDTO> list = new ArrayList<UserIssuesHistoryDTO>();
        listOfHistoriesByUserID.forEach(historyModel -> {
            issues.forEach(issueModel -> {
                if (issueModel.getId() == historyModel.getIssue().getId()) {
                    list.add(getSingleUserIssuesHistoryDTO(historyModel, issueModel, userModel));
                }
            });
        });
        return list;
    }

    public static UserIssuesHistoryDTO getSingleUserIssuesHistoryDTO(HistoryModel historyModel, IssueModel issueModel, UserModel userModel) {

        UserIssuesHistoryDTO userIssuesHistoryDto = new UserIssuesHistoryDTO();
        userIssuesHistoryDto.setIssueName(issueModel.getName());
        IssueHistoryDTO issueHistoryDto = new IssueHistoryDTO();
        issueHistoryDto.setDate(historyModel.getDate());
        issueHistoryDto.setChangedByUser(userModel.getName());
        issueHistoryDto.setStatus(historyModel.getStatus().name());
        userIssuesHistoryDto.setIssueHistoryDto(issueHistoryDto);
        userIssuesHistoryDto.setCurrentStatus(issueModel.getStatus().name());

        return userIssuesHistoryDto;
    }


    public static List<UserHistoryDTO> getUserHistoryDtos(IssueModel issue, UserService userService) {
        List<UserHistoryDTO> historyDtoList = new ArrayList<UserHistoryDTO>();
        if (null == issue) {
            return historyDtoList;
        }
        for(HistoryModel historyModel : issue.getHistories()){
              UserHistoryDTO userHistoryDto = getUserHistoryDto(historyModel, issue, userService);
              historyDtoList.add(userHistoryDto);
        }
        Collections.sort(historyDtoList);
        return historyDtoList;
    }

    public static UserHistoryDTO getUserHistoryDto(HistoryModel historyModel, IssueModel issueModel, UserService userService){
        UserHistoryDTO userHistoryDto = new UserHistoryDTO();
        userHistoryDto.setStatus(historyModel.getStatus().name());
        userHistoryDto.setDate(historyModel.getDate());
        userHistoryDto.setIssueName(issueModel.getName());
        UserModel userModel = userService.getById(historyModel.getUserId());
        userHistoryDto.setRoleName(userModel.getRole().caption);
        userHistoryDto.setUsername(userModel.getName());
        return userHistoryDto;
    }


    public  static List<CommentDTO> getCommentsFrom(List<CommentModel> comments) {
        List<CommentDTO> commentsDTO = new ArrayList<>();
        comments.forEach(commentModel -> {
            commentsDTO.add(getCommentFrom(commentModel));
        });
        return commentsDTO;
    }

    public static CommentDTO getCommentFrom(CommentModel commentModel) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(commentModel.getId());
        commentDTO.setIssueId(commentModel.getIssueId());
        commentDTO.setComment(commentModel.getComment());
        commentDTO.setUserName(commentModel.getUserName());
        commentDTO.setEmail(commentModel.getEmail());
        return commentDTO;
    }
}
