package edu.com.softserveinc.bawl.services;

import edu.com.softserveinc.bawl.AbstractBawlTest;
import edu.com.softserveinc.bawl.dao.HistoryDao;
import edu.com.softserveinc.bawl.dao.IssueDao;
import edu.com.softserveinc.bawl.models.HistoryModel;
import edu.com.softserveinc.bawl.models.IssueModel;
import edu.com.softserveinc.bawl.services.impl.IssueServiceImpl;
import edu.com.softserveinc.bawl.utils.CsvIssueModelReaderWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;

/**
 * Created by Vasyl on 21.10.2015.
 */

public class IssueServiceImplTest extends AbstractBawlTest {

    private IssueService issueService;
    private IssueDao issueDao;
    private HistoryDao historyDao;

    @Before
    public void setUp() {
        issueService = new IssueServiceImpl();
        issueDao = mock(IssueDao.class);
        Whitebox.setInternalState(issueService, "issueDao", issueDao);
        historyDao = mock(HistoryDao.class);
        Whitebox.setInternalState(issueService, "historyDao", historyDao);
//        when(issueDao.findAll()).thenReturn(getTestingIssueModel());
//        org.powermock.reflect.Whitebox.invokeMethod(issueService, "saveToHistory", issueDao, );
    }
//    private List<IssueModel> getTestingIssueModel(){
//        List<IssueModel> issues = null;
//        try {
//            issues = CsvIssueModelReaderWriter.readIssueModelCsv();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return issues;
//    }

//    @After
//    public void tearDown() throws Exception {
//
//    }

    @Test
    public void testAddProblem_shouldCallSaveAndFlushIssueToDao() throws Exception {//powermock test private methods
        IssueModel mockIssueModel = mock(IssueModel.class);
        final int userId = 1;
        issueService.addProblem(mockIssueModel, userId);
        verify(issueDao, times(1)).saveAndFlush(mockIssueModel);
//        HistoryModel historyModel = new HistoryModel();
// //       historyModel.setStatusId(1);//Так не можна бо mockIssueModel - це мок де нічого немає
// //       historyModel.setIssueId(2);
//       // final int userId = 1;
//        historyModel.setUserId(userId);
//        issueService.addProblem(mockIssueModel, userId);
//        verify(issueDao, times(1)).saveAndFlush(mockIssueModel);
////        assertEquals(mockIssueModel.getId(), historyModel.getIssueId());
//        org.powermock.reflect.Whitebox.invokeMethod(issueService, "saveToHistory", mockIssueModel, userId);
//
//        verify(historyDao, times(1)).saveAndFlush(historyModel);
    }

    @Test
    public void testEditProblem_shouldCallSaveAndFlushIssueToDao() throws Exception {
        IssueModel mockIssueModel = mock(IssueModel.class);
        final int userId = 1;
        issueService.editProblem(mockIssueModel, userId);
        verify(issueDao, times(1)).saveAndFlush(mockIssueModel);
    }

    @Test
    public void testDeleteProblem() throws Exception {
        IssueModel issueTestModel = new IssueModel();
        final int userId = 1;
        issueService.deleteProblem(issueTestModel, userId);
        assertEquals(4, issueTestModel.getStatusId());
//        public void deleteCategory_shouldChangeIsdeletedTo1AndSaveToDao() {
//            +        CategoryModel testModel = new CategoryModel();
//            +        categoryService.deleteCategory(testModel);
//            +        verify(categoryDao, times(1)).saveAndFlush(testModel);
//            +        assertEquals(1, testModel.getIsdeleted());
//            +    }
    }

    @Test
    public void testDeleteProblem1() throws Exception {
        IssueModel issueTestModel = new IssueModel();
        final int issueId = 2;
        issueTestModel.setId(issueId);
        when(issueDao.findOne(issueId)).thenReturn(issueTestModel);

        IssueModel actualModel = issueDao.findOne(issueId);
        IssueModel expectedModel = new IssueModel();
        expectedModel.setId(issueId);
        verify(issueDao, times(1)).findOne(issueId);
        assertEquals(expectedModel, actualModel);

        final int userId = 1;
        issueService.deleteProblem(issueId, userId);
        assertEquals(4, actualModel.getStatusId());
    }

//    public void getStatusByName_shouldReturnFinfByNameFromDao () {
//
//        StatusModel statusModel = new StatusModel();
//        String name = "username";
//        statusModel.setName(name);
//        when(statusDao.findByName(name)).thenReturn(statusModel);
//        StatusModel actualModel = statusService.getStatusByName(name);
//
//        StatusModel expectedModel = new StatusModel();
//        expectedModel.setName(name);
//
//        verify(statusDao, times(1)).findByName(name);
//        assertEquals(expectedModel, actualModel);
//    }
//    +    public void getCategoryByID_shouldReturnCategoryByConcreteID() {
//        +        int categoryID = 2;
//        +        CategoryModel testModel = new CategoryModel();
//        +        testModel.setId(categoryID);
//        +        assertEquals(categoryID, testModel.getId());
//        +    }

    @Test
    public void testLoadIssuesList() throws Exception {
        //List<IssueModel> findIssueModels = issueService.loadIssuesList();
        IssueDao issueDao = mock(IssueDao.class);
        IssueModel issueModel1 = new IssueModel();
        issueModel1.setId(1);
        IssueModel issueModel2 = new IssueModel();
        issueModel2.setId(2);
        IssueModel issueModel3 = new IssueModel();
        issueModel3.setId(3);
        List<IssueModel> expectedModels = new ArrayList<>();
        expectedModels.add(issueModel1);
        expectedModels.add(issueModel2);
        expectedModels.add(issueModel3);

        when(issueDao.findOne(1)).thenReturn(issueModel1);
        when(issueDao.findOne(2)).thenReturn(issueModel2);
        when(issueDao.findOne(3)).thenReturn(issueModel3);
    }
}
