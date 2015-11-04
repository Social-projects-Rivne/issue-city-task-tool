package edu.com.softserveinc.bawl.services;

import edu.com.softserveinc.bawl.AbstractBawlTest;
import edu.com.softserveinc.bawl.dao.HistoryDao;
import edu.com.softserveinc.bawl.dao.IssueDao;
import edu.com.softserveinc.bawl.models.IssueModel;
import edu.com.softserveinc.bawl.models.enums.IssueStatus;
import edu.com.softserveinc.bawl.services.impl.IssueServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;

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
    }

    @Test
    public void testAddProblem_shouldCallSaveAndFlushIssueToDao() throws Exception {//powermock test private methods
        IssueModel mockIssueModel = mock(IssueModel.class);
        final int userId = 1;
        issueService.addProblem(mockIssueModel, userId);
        verify(issueDao, times(1)).saveAndFlush(mockIssueModel);
    }

    @Test
    public void testEditProblem_shouldCallSaveAndFlushIssueToDao() throws Exception {
        IssueModel mockIssueModel = mock(IssueModel.class);
        final int userId = 1;
        issueService.editProblem(mockIssueModel, userId);
        verify(issueDao, times(1)).saveAndFlush(mockIssueModel);
    }

    @Test
    public void testDeleteProblem_setStatusDeletedToIssueModel() throws Exception {
        IssueModel issueTestModel = new IssueModel();
        final int userId = 1;
        issueService.deleteProblem(issueTestModel, userId);
        assertEquals(IssueStatus.DELETED, issueTestModel.getStatus());
    }

    @Test
    public void testDeleteProblem_findOneIssueModelByIssueIdAndSetStatusDeleted() throws Exception {
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
        assertEquals(IssueStatus.DELETED, actualModel.getStatus());
    }

    @Test
    public void testLoadIssuesList_shouldReturnListOfAllIssueModels() throws Exception {
        issueService.loadIssuesList();
        verify(issueDao, times(1)).findAll();
    }

    @Test
    public void testGetById_shouldReturnIssueModelByIssueID() throws Exception {
        IssueModel testIssueModel = new IssueModel();
        final int issueId = 2;
        testIssueModel.setId(issueId);
        when(issueDao.findOne(issueId)).thenReturn(testIssueModel);

        IssueModel actualModel = issueDao.findOne(issueId);
        IssueModel expectedModel = new IssueModel();
        expectedModel.setId(issueId);
        verify(issueDao, times(1)).findOne(issueId);
        assertEquals(expectedModel, actualModel);
    }

}
