package edu.com.softserveinc.bawl.dto;

import edu.com.softserveinc.bawl.models.HistoryModel;
import edu.com.softserveinc.bawl.models.IssueModel;
import edu.com.softserveinc.bawl.models.UserModel;
import edu.com.softserveinc.bawl.services.UserService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Utility class for mapping domain models into DTOs consumed by REST controllers
 */
public class DTOMapper {

    /**
     * Returns all issues
     * @param allIssues
     * @return
     */
    public static List<IssueDto> getAllIssuesDto(List<IssueModel> allIssues){
        List<IssueDto> listIssueDto = new ArrayList<IssueDto>(allIssues.size());
        allIssues.forEach( issueModel -> {
                    IssueDto issueDto = new IssueDto();
                    issueDto.setName(issueModel.getName());
                    issueDto.setDescription(issueModel.getDescription());
                    issueDto.setAttachments(issueModel.getAttachments());
                    issueDto.setMapPointer(issueModel.getMapPointer());
                    issueDto.setCategoryId(issueModel.getCategoryId());
                    issueDto.setPriorityId(issueModel.getPriorityId());
                    issueDto.setStatusId(issueModel.getStatusId());
                    listIssueDto.add(issueDto);
                }
        );
        return listIssueDto;
    }

    public static List<UserIssuesHistoryDto> getAllUserIssuesHistoryDTO(List<HistoryModel> listOfHistoriesByUserID, List<IssueModel> issues, UserModel userModel) {
        List<UserIssuesHistoryDto> list = new ArrayList<UserIssuesHistoryDto>();
        listOfHistoriesByUserID.forEach(historyModel -> {
            issues.forEach(issueModel -> {
                if (issueModel.getId() == historyModel.getIssueId()) {
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
        issueHistoryDto.setStatus(historyModel.getStatusId());
        userIssuesHistoryDto.setIssueHistoryDto(issueHistoryDto);
        userIssuesHistoryDto.setCurrentStatus(issueModel.getStatusId());

        return userIssuesHistoryDto;
    }


    public static List<UserHistoryDto> getUserHistoryDtos(List<HistoryModel> histories, List<IssueModel> allIssues, UserService userService) {
        List<UserHistoryDto> historyDtoList = new ArrayList<UserHistoryDto>();
        //join 2 tables on IssueId
        for(HistoryModel historyModel : histories){
            for(IssueModel issueModel : allIssues){
                if (issueModel.getId() == historyModel.getIssueId()){
                    UserHistoryDto userHistoryDto = getUserHistoryDto(historyModel, issueModel, userService);
                    historyDtoList.add(userHistoryDto);

                }
            }
        }
        Collections.sort(historyDtoList, (p1, p2) -> p1.getDate().compareTo(p2.getDate()));
        return historyDtoList;
    }

    public static UserHistoryDto getUserHistoryDto(HistoryModel historyModel, IssueModel issueModel, UserService userService){
        UserHistoryDto userHistoryDto = new UserHistoryDto();
        userHistoryDto.setStatusId(historyModel.getStatusId());
        userHistoryDto.setDate(historyModel.getDate());
        userHistoryDto.setIssueName(issueModel.getName());
        UserModel userModel = userService.getById(historyModel.getUserId());
        String roleName = userModel.getRole_id() == 0 ? "User" : "Manager";
        userHistoryDto.setRoleName(roleName);
        userHistoryDto.setUsername(userModel.getName());
        return userHistoryDto;
    }


}
