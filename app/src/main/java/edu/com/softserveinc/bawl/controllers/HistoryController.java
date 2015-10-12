package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.dto.UserHistoryDto;
import edu.com.softserveinc.bawl.dto.IssueHistoryDto;
import edu.com.softserveinc.bawl.dto.UserIssuesHistoryDto;
import edu.com.softserveinc.bawl.models.HistoryModel;
import edu.com.softserveinc.bawl.models.IssueModel;
import edu.com.softserveinc.bawl.models.UserModel;
import edu.com.softserveinc.bawl.services.HistoryService;
import edu.com.softserveinc.bawl.services.IssueService;
import edu.com.softserveinc.bawl.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Illia on 10/8/2015.
 */
@Controller
public class HistoryController {
    /**
     * Logger field
     */
    public static final Logger LOG=Logger.getLogger(UserController.class);

    @Autowired
    private IssueService issueService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "issue/{id}/history", method = RequestMethod.GET)
    public @ResponseBody List<UserHistoryDto> getUserHistoryAction(
            @PathVariable int id ) {

        List<UserHistoryDto> historyDtoList = new ArrayList<UserHistoryDto>();
        List<HistoryModel> histories = historyService.getHistoriesByIssueID(id);
        List<IssueModel> allIssues = issueService.loadIssuesList();

        //join 2 tables on IssueId
        for(HistoryModel historyModel : histories){
            for(IssueModel issueModel : allIssues){
                if (issueModel.getId() == historyModel.getIssueId()){
                    UserHistoryDto userHistoryDto = getUserHistoyDto(historyModel, issueModel);
                    historyDtoList.add(userHistoryDto);

                }
            }
        }

        historyDtoList.sort(new Comparator<UserHistoryDto>() {
            @Override
            public int compare(UserHistoryDto historyDto1, UserHistoryDto historyDto12) {
                return historyDto1.getDate().compareTo(historyDto12.getDate());
            }
        });
        return historyDtoList;
    }

    private UserHistoryDto getUserHistoyDto(HistoryModel historyModel, IssueModel issueModel ){
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

    @RequestMapping(value = "user/{id}/history", method = RequestMethod.GET)
    public @ResponseBody List<UserIssuesHistoryDto> getUserIssuesHistories(@PathVariable int id){
        List<UserIssuesHistoryDto> list = new ArrayList<UserIssuesHistoryDto>();

        List<HistoryModel> listOfHistoriesByUserID = historyService.getHistoriesByUserID(id);

        List<IssueModel> issues = issueService.loadIssuesList();

        for ( HistoryModel historyModel : listOfHistoriesByUserID){
            for(IssueModel issueModel : issues){
                if(issueModel.getId()==historyModel.getIssueId())
                {
                    UserIssuesHistoryDto uihdto = new UserIssuesHistoryDto();
                    uihdto.setIssueName(issueModel.getName());

                    IssueHistoryDto ihdto= new IssueHistoryDto();
                    ihdto.setDate(historyModel.getDate());
                    UserModel um =userService.getById(historyModel.getUserId());
                    ihdto.setChangedByUser(um.getName());
                    ihdto.setStatus(historyModel.getStatusId());

                    uihdto.setIssueHistoryDto(ihdto);

                    uihdto.setCurrentStatus(issueModel.getStatusId());

                    list.add(uihdto);
                }

            }

        }

        return list;
    }


}
