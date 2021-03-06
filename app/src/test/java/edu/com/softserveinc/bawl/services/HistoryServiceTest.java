package edu.com.softserveinc.bawl.services;

import edu.com.softserveinc.bawl.AbstractBawlFunctionalTest;
import edu.com.softserveinc.bawl.dao.HistoryDao;
import edu.com.softserveinc.bawl.dao.IssueDao;
import edu.com.softserveinc.bawl.models.HistoryModel;
import edu.com.softserveinc.bawl.models.IssueModel;
import edu.com.softserveinc.bawl.models.enums.IssueStatus;
import edu.com.softserveinc.bawl.services.impl.HistoryServiceImpl;
import edu.com.softserveinc.bawl.utils.CsvReaderWriter;
import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HistoryServiceTest extends AbstractBawlFunctionalTest {

    private HistoryService historyService = null;

    private HistoryDao historyDao;
    private IssueDao issueDao;

    @Before
    public void setup() {
        historyService = new HistoryServiceImpl();
        historyDao = mock(HistoryDao.class);
        Whitebox.setInternalState(historyService, "historyDao", historyDao);
        issueDao = mock(IssueDao.class);
        when(historyDao.findAll()).thenReturn(getTestHistoryModels());
    }

    private  List <HistoryModel> getTestHistoryModels(){
        List <HistoryModel> histories = null;
        try {
            histories = CsvReaderWriter.readHistoryModelCsv();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return histories;
    }

    @Test
    public void addHistory_shouldCallAddHistoryToDao(){
        HistoryModel mockHistoryModel = mock(HistoryModel.class);

        historyService.addHistory(mockHistoryModel);

        verify(historyDao, times(1)).saveAndFlush(mockHistoryModel);

    }

    @Test
    public void editHistory_shouldCallAddHistoryToDao(){
        HistoryModel mockHistoryModel = mock(HistoryModel.class);

        historyService.editHistory(mockHistoryModel);

        verify(historyDao, times(1)).saveAndFlush(mockHistoryModel);
    }

    @Test
    public void deleteHistory_shouldChangeStatusIdTo4AndSaveToDao(){
        HistoryModel historyModel = new HistoryModel();

        historyService.deleteHistory(historyModel);

        verify(historyDao, times(1)).saveAndFlush(historyModel);
        assertEquals(IssueStatus.DELETED, historyModel.getStatus());
    }


    @Test
    public void getHistoriesByUserID_shouldReturnListHistoriesByConcreteUser(){
        int userId = 2;
        List<HistoryModel> findHistories = historyService.getHistoriesByUserID(userId);

        for(HistoryModel model : findHistories){
            assertEquals(userId, model.getUserId() );
        }
    }

    @Test
    public void getHistoriesByIssueID_shouldReturnListHistoriesByConcreteIssueId(){

        int issueId = 2;
        List<HistoryModel> findHistories = historyService.getHistoriesByIssueID(issueId);

        for(HistoryModel model : findHistories){
            assertEquals(issueId, model.getIssue().getId() );

        }
    }

    @Test
    public void getLastUniqueIssues_shouldReturnUniqueIssues(){

        IssueModel issueModel1 = new IssueModel();
        issueModel1.setId(1);
        issueModel1.setStatus(IssueStatus.NEW);
        IssueModel issueModel2 = new IssueModel();
        issueModel2.setId(2);
        issueModel2.setStatus(IssueStatus.NEW);
        HistoryModel historyModel1 = new HistoryModel();
        historyModel1.setIssue(issueModel1);
        HistoryModel historyModel2 = new HistoryModel();
        historyModel2.setIssue(issueModel2);
        List<HistoryModel> historyModels = new ArrayList<>();
        historyModels.add(historyModel1);
        historyModels.add(historyModel2);
        when(issueDao.findOne(1)).thenReturn(issueModel1);
        when(issueDao.findOne(2)).thenReturn(issueModel2);
        when(historyDao.getUniqueLastByDateHistories()).thenReturn(historyModels);
        List<IssueModel> expectedIssueModels = new ArrayList<>();
        IssueModel expectedIssueModel1 = new IssueModel();
        expectedIssueModel1.setId(1);
        expectedIssueModel1.setStatus(IssueStatus.NEW);
        IssueModel expectedIssueModel2 = new IssueModel();
        expectedIssueModel2.setId(2);
        expectedIssueModel2.setStatus(IssueStatus.NEW);
        expectedIssueModels.add(expectedIssueModel1);
        expectedIssueModels.add(expectedIssueModel2);

        List<IssueModel> actualIssueModels = historyService.getLastUniqueIssues();

        verify(historyDao).getUniqueLastByDateHistories();
        assertThat(actualIssueModels, is(expectedIssueModels) );

    }

    @Test
    public void getLastIssueByIssueID_shouldReturnLastIssueByIssueID(){
        int issueId = 1;
        IssueModel issueModel = new IssueModel();
        issueModel.setId(issueId);
        issueModel.setStatus(IssueStatus.NEW);
        HistoryModel historyModel = mock(HistoryModel.class);
        when(historyDao.getLastByIssueIDHistory(issueId)).thenReturn(historyModel);
        when(historyModel.getIssue()).thenReturn(issueModel);
        when(historyModel.getStatus()).thenReturn(IssueStatus.NEW);
        when(issueDao.findOne(1)).thenReturn(issueModel);
        IssueModel expectedModel = new IssueModel();
        expectedModel.setId(issueId);
        expectedModel.setStatus(IssueStatus.NEW);

        IssueModel actualModel = historyService.getLastIssueByIssueID(issueId);

        assertEquals(actualModel, expectedModel);
    }







}