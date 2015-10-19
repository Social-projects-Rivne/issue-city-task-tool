package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.dto.DTOMapper;
import edu.com.softserveinc.bawl.dto.UserHistoryDto;
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


        List<HistoryModel> histories = historyService.getHistoriesByIssueID(id);
        List<IssueModel> allIssues = issueService.loadIssuesList();

        List<UserHistoryDto> historyDtoList = DTOMapper.getUserHistoryDtos(histories, allIssues, userService);
        return historyDtoList;
    }

    @RequestMapping(value = "user/{id}/history", method = RequestMethod.GET)
    public @ResponseBody List<UserIssuesHistoryDto> getUserIssuesHistories(@PathVariable int id){
        List<UserIssuesHistoryDto> list = new ArrayList<UserIssuesHistoryDto>();

        List<HistoryModel> listOfHistoriesByUserID = historyService.getHistoriesByUserID(id);
        List<IssueModel> issues = issueService.loadIssuesList();
        UserModel userModel = userService.getById(id);

        for ( HistoryModel historyModel : listOfHistoriesByUserID){
            for(IssueModel issueModel : issues){
                if(issueModel.getId()==historyModel.getIssueId())
                {
                    UserIssuesHistoryDto uihdto = DTOMapper.getUserIssuesHistoryDto(historyModel, issueModel, userModel);

                    list.add(uihdto);
                }

            }

        }

        return list;
    }


}
