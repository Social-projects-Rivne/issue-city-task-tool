package edu.com.softserveinc.bawl.services.impl;

import edu.com.softserveinc.bawl.dao.CommentDao;
import edu.com.softserveinc.bawl.models.CommentModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.reflect.Whitebox;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Oleg on 20.10.2015.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-root-context.xml", "classpath:test-data-context.xml","classpath:test-mail-context.xml"} )

public class CommentServiceImplTest extends Mockito {



    private CommentServiceImpl commentService ;
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
        commentService.addComment(mockCommentModel);
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
        commentService.editComment(mockCommentModel);
        verify(commentDao, times(1)).saveAndFlush(mockCommentModel);
    }

    @Test
    public void testGetCommentsByIssueId() throws Exception {

            int IssueId = 2;
            List<CommentModel> findComments = commentService.getCommentsByIssueId(IssueId);

            for(CommentModel model : findComments){
                assertEquals(IssueId, model.getIssueId() );
            }
        }
    }