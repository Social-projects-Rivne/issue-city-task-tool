package edu.com.softserveinc.bawl.services;

import edu.com.softserveinc.bawl.AbstractBawlTest;
import edu.com.softserveinc.bawl.dao.HistoryDao;
import edu.com.softserveinc.bawl.dao.IssueDao;
import edu.com.softserveinc.bawl.models.HistoryModel;
import edu.com.softserveinc.bawl.models.IssueModel;
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
import static org.mockito.Mockito.*;


public class HistoryServiceTest extends AbstractBawlTest {

    private HistoryService historyService = null;

    private HistoryDao historyDao;
    private IssueDao issueDao;

    @Before
    public void setup() {
        historyService = new HistoryServiceImpl();
        historyDao = mock(HistoryDao.class);
        Whitebox.setInternalState(historyService, "historyDao", historyDao);
        issueDao = mock(IssueDao.class);
        Whitebox.setInternalState(historyService, "issueDao", issueDao);
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
        assertEquals(4, historyModel.getStatusId());
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
            assertEquals(issueId, model.getIssueId() );

        }
    }

    @Test
    public void getLastUniqueIssues_shouldReturnUniqueIssues(){

        IssueModel issueModel1 = new IssueModel();
        issueModel1.setId(1);
        IssueModel issueModel2 = new IssueModel();
        issueModel2.setId(2);
        HistoryModel historyModel1 = new HistoryModel();
        historyModel1.setIssueId(1);
        HistoryModel historyModel2 = new HistoryModel();
        historyModel2.setIssueId(2);
        List<HistoryModel> historyModels = new ArrayList<>();
        historyModels.add(historyModel1);
        historyModels.add(historyModel2);
        when(issueDao.findOne(1)).thenReturn(issueModel1);
        when(issueDao.findOne(2)).thenReturn(issueModel2);
        when(historyDao.getUniqueLastByDateHistories()).thenReturn(historyModels);
        List<IssueModel> expectedIssueModels = new ArrayList<>();
        IssueModel expectedIssueModel1 = new IssueModel();
        expectedIssueModel1.setId(1);
        IssueModel expectedIssueModel2 = new IssueModel();
        expectedIssueModel2.setId(2);
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
        HistoryModel historyModel = mock(HistoryModel.class);
        when(historyDao.getLastByIssueIDHistory(issueId)).thenReturn(historyModel);
        when(historyModel.getIssueId()).thenReturn(issueId);
        when(issueDao.findOne(1)).thenReturn(issueModel);
        IssueModel expectedModel = new IssueModel();
        expectedModel.setId(issueId);

        IssueModel actualModel = historyService.getLastIssueByIssueID(issueId);

        assertThat(actualModel, is(expectedModel));
    }







}