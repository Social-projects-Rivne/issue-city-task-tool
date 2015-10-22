package edu.com.softserveinc.bawl.services;

import edu.com.softserveinc.bawl.dao.HistoryDao;
import edu.com.softserveinc.bawl.dao.IssueDao;
import edu.com.softserveinc.bawl.models.HistoryModel;
import edu.com.softserveinc.bawl.models.IssueModel;
import edu.com.softserveinc.bawl.services.impl.HistoryServiceImpl;

import edu.com.softserveinc.bawl.utils.CsvReaderWriter;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;


import org.powermock.reflect.Whitebox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.is;/*
import static org.powermock.api.mockito.PowerMockito.verifyPrivate;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.spy;*/

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;


/**
 * Created by Illia on 10/12/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-root-context.xml", "classpath:test-data-context.xml","classpath:test-mail-context.xml"} )

public class HistoryServiceTest {

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


    //expected 3 unique issues from historyDao
    @Test
    public void getLastUniqueIssues_shouldReturnUniqueIssues(){

        IssueDao issueDao = mock(IssueDao.class);
        IssueModel issueModel1 = new IssueModel();
        issueModel1.setId(1);
        IssueModel issueModel2 = new IssueModel();
        issueModel2.setId(2);

        List<IssueModel> issueModels = new ArrayList<IssueModel>();
        issueModels.add(issueModel1);
        issueModels.add(issueModel2);

        HistoryModel historyModel1 = mock(HistoryModel.class);
        when(historyModel1.getIssueId()).thenReturn(1);
        HistoryModel historyModel2 = mock(HistoryModel.class);
        when(historyModel2.getIssueId()).thenReturn(2);
        List<HistoryModel> historyModels = new ArrayList<HistoryModel>();
        historyModels.add(historyModel1);
        historyModels.add(historyModel2);
        when(issueDao.findOne(1)).thenReturn(issueModel1);
        when(issueDao.findOne(2)).thenReturn(issueModel2);
        when(historyDao.getUniqueLastByDateHistories()).thenReturn(historyModels);
        List<IssueModel> expectedIssueModels = new ArrayList<IssueModel>();
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