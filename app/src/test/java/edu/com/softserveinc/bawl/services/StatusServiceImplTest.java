package edu.com.softserveinc.bawl.services;

import edu.com.softserveinc.bawl.AbstractBawlTest;
import edu.com.softserveinc.bawl.dao.StatusDao;
import edu.com.softserveinc.bawl.models.StatusModel;
import edu.com.softserveinc.bawl.services.impl.StatusServiceImpl;
import edu.com.softserveinc.bawl.utils.CsvReaderWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.reflect.Whitebox;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.List;


public class StatusServiceImplTest extends AbstractBawlTest {
    private StatusService statusService = null;
    private StatusDao statusDao;

    @Before
    public void setup() {
        statusService = new StatusServiceImpl();
        statusDao = mock(StatusDao.class);
        Whitebox.setInternalState(statusService, "statusDao", statusDao);
    }



        @Test
        public void addStatus_shouldCallAddStatusToDao () {
            StatusModel mockStatusModel = mock(StatusModel.class);

            statusService.addStatus(mockStatusModel);

            verify(statusDao, times(1)).saveAndFlush(mockStatusModel);

        }

        @Test
        public void getStatusByName_shouldReturnFinfByNameFromDao () {

            StatusModel statusModel = new StatusModel();
            String name = "username";
            statusModel.setName(name);
            when(statusDao.findByName(name)).thenReturn(statusModel);
            StatusModel actualModel = statusService.getStatusByName(name);

            StatusModel expectedModel = new StatusModel();
            expectedModel.setName(name);

            verify(statusDao, times(1)).findByName(name);
            assertEquals(expectedModel, actualModel);
        }


        @Test
    public void loadStatusList_shouldReturnfindAllFromDao (){


        }

    }

