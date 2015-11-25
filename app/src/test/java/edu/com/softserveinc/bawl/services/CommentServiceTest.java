package edu.com.softserveinc.bawl.services;

import edu.com.softserveinc.bawl.AbstractBawlFunctionalTest;
import edu.com.softserveinc.bawl.dao.CommentDao;
import edu.com.softserveinc.bawl.models.CommentModel;
import edu.com.softserveinc.bawl.services.impl.CommentServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CommentServiceTest extends AbstractBawlFunctionalTest {

    private CommentService commentService;
    private CommentDao commentDao;

    @Before
    public void setup() {
        commentService = new CommentServiceImpl();
        commentDao = mock(CommentDao.class);
        Whitebox.setInternalState(commentService, "commentDao", commentDao);
    }

    @Test
    public void testAddComment() throws Exception {
        CommentModel mockCommentModel = mock(CommentModel.class);
        commentService.saveComment(mockCommentModel);
        verify(commentDao, times(1)).saveAndFlush(mockCommentModel);
    }

    @Test
    public void testDeleteComment() throws Exception {
        CommentModel mockCommentModel = mock(CommentModel.class);
        commentService.deleteComment(mockCommentModel);
        verify(commentDao, times(1)).delete(mockCommentModel);
    }

    @Test
    public void testEditComment() throws Exception {
        CommentModel mockCommentModel = mock(CommentModel.class);
        commentService.saveComment(mockCommentModel);
        verify(commentDao, times(1)).saveAndFlush(mockCommentModel);
    }

    @Test
    public void testGetCommentsByIssueId() throws Exception {
        int IssueId = 2;
        List<CommentModel> findComments = commentService.getCommentsByIssueId(IssueId);
        for (CommentModel model : findComments) {
            assertEquals(IssueId, model.getIssueId());
        }
    }

    @Test
    public void testGetCommentsByIssueId1() throws Exception {
        CommentModel commentModel = new CommentModel();
        int IssueId = 4;
        commentModel.setIssueId(IssueId);
        when(commentDao.findByIssueId(IssueId)).thenReturn(Collections.singletonList(commentModel));
        List<CommentModel> actualModel = commentService.getCommentsByIssueId(IssueId);
        CommentModel expectedModel = new CommentModel();
        expectedModel.setIssueId(IssueId);
        verify(commentDao).findByIssueId(IssueId);
        assertEquals(Collections.singletonList(expectedModel),actualModel);

    }
}