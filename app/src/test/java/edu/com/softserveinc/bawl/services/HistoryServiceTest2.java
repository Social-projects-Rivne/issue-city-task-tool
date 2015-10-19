package edu.com.softserveinc.bawl.services;

import edu.com.softserveinc.bawl.dao.HistoryDao;
import edu.com.softserveinc.bawl.models.HistoryModel;
import edu.com.softserveinc.bawl.services.impl.HistoryServiceImpl;

import edu.com.softserveinc.bawl.utils.CsvReaderWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


import org.powermock.reflect.Whitebox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;


/**
 * Created by Illia on 10/12/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-root-context.xml", "classpath:test-data-context.xml","classpath:test-mail-context.xml"} )

public class HistoryServiceTest2 {

    private HistoryService historyService = null;
    private HistoryDao historyDao = null;



    @Before
    public void setup() {
        historyService = new HistoryServiceImpl();
        historyDao = mock(HistoryDao.class);
        Whitebox.setInternalState(historyService, "historyDao", historyDao);
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





}