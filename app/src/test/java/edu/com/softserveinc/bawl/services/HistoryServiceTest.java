package edu.com.softserveinc.bawl.services;

import edu.com.softserveinc.bawl.models.HistoryModel;
import edu.com.softserveinc.bawl.models.IssueModel;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Illia on 10/11/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-root-context.xml", "classpath:test-data-context.xml","classpath:test-mail-context.xml"
        ,"classpath:test-root-context.xml"})
public class HistoryServiceTest {

    @Autowired
    private HistoryService historyService;

    /*@Autowired
    private IssueDao issueDao;

    @Before
    public void setup() {
        List <HistoryModel> historyModels = new ArrayList<>();

        HistoryModel historyModel = new HistoryModel();
        historyModel.setStatusId(0);
        historyModel.setId(1);
        historyModel.setIssueId(1);

        historyModels.add(historyModel);
        when(historyDao.findAll()).thenReturn(historyModels);
    }*/

    @Test
    public void getHistoriesByUserID_shouldReturnListHistoriesByConcreteUser(){

        int userId = 1;
        List<HistoryModel> findHistories = historyService.getHistoriesByUserID(userId);
        for(HistoryModel model : findHistories){
            assertEquals(userId, model.getUserId() );
        }
    }

    @Test
    public void getHistoriesByIssueID_shouldReturnListHistoriesByConcreteIssueId() {

        int issueId = 1;
        List<HistoryModel> findHistories = historyService.getHistoriesByIssueID(issueId);
        for(HistoryModel model : findHistories){
            assertEquals(issueId, model.getIssueId() );
        }

    }

    @Test
    public void getLastUniqueIssues_shouldReturnLastByDateUniqueByIssueIdIssues() {

        List<IssueModel> findIssues = historyService.getLastUniqueIssues();
        int repeatCounter = 0;
        for (IssueModel issueModel : findIssues) {
            repeatCounter = 0;
            for (IssueModel curIssue : findIssues) {
                if ( curIssue.getId() == issueModel.getId()){
                    repeatCounter++;
                }

            }
            assertEquals(1, repeatCounter);

        }
    }




}