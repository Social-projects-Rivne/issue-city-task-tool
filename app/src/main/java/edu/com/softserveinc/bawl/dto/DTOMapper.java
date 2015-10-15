package edu.com.softserveinc.bawl.dto;

import edu.com.softserveinc.bawl.dto.comparators.UserHistoryDtoComparator;
import edu.com.softserveinc.bawl.models.HistoryModel;
import edu.com.softserveinc.bawl.models.IssueModel;
import edu.com.softserveinc.bawl.models.UserModel;
import edu.com.softserveinc.bawl.services.UserService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Oleg on 13.10.2015.
 */
public class DTOMapper {
    public static UserIssuesHistoryDto getUserIssuesHistoryDto(HistoryModel historyModel, IssueModel issueModel, UserService userService) {
        UserIssuesHistoryDto userIssuesHistoryDto = new UserIssuesHistoryDto();
        userIssuesHistoryDto.setIssueName(issueModel.getName());

        IssueHistoryDto issueHistoryDtodto = new IssueHistoryDto();
        issueHistoryDtodto.setDate(historyModel.getDate());
        UserModel um =userService.getById(historyModel.getUserId());
        issueHistoryDtodto.setChangedByUser(um.getName());
        issueHistoryDtodto.setStatus(historyModel.getStatusId());

        userIssuesHistoryDto.setIssueHistoryDto(issueHistoryDtodto);

        userIssuesHistoryDto.setCurrentStatus(issueModel.getStatusId());
        return userIssuesHistoryDto;
    }

    public static UserHistoryDto getUserHistoyDto(HistoryModel historyModel, IssueModel issueModel, UserService userService ){
        UserHistoryDto userHistoryDto = new UserHistoryDto();
        userHistoryDto.setStatusId(historyModel.getStatusId());
        userHistoryDto.setDate(historyModel.getDate());
        userHistoryDto.setIssueName(issueModel.getName());
        String roleName = userService
                .getById(historyModel.getUserId())
                .getRole_id() == 0 ? "User" : "Manager";
        userHistoryDto.setRoleName(roleName);
        userHistoryDto.setUsername(userService
                .getById(historyModel.getUserId())
                .getName());
        return userHistoryDto;
    }

    public static List<UserHistoryDto> getUserHistoryDtos(List<HistoryModel> histories, List<IssueModel> allIssues, UserService userService) {
        List<UserHistoryDto> historyDtoList = new ArrayList<UserHistoryDto>();
        //join 2 tables on IssueId
        for(HistoryModel historyModel : histories){
            for(IssueModel issueModel : allIssues){
                if (issueModel.getId() == historyModel.getIssueId()){
                    UserHistoryDto userHistoryDto = getUserHistoyDto(historyModel, issueModel, userService);
                    historyDtoList.add(userHistoryDto);

                }
            }
        }


        Collections.sort(historyDtoList, new UserHistoryDtoComparator());
        return historyDtoList;
    }

    public static List<IssueDto> getAllIssuesDto(List<IssueModel> allIssues){

        List<IssueDto> listIssueDto =new ArrayList<IssueDto>();

        for(IssueModel issueModel :  allIssues){
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
        return listIssueDto;
    }
}
