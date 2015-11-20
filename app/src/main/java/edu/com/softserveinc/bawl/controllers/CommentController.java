package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.dto.pojo.CommentDTO;
import edu.com.softserveinc.bawl.dto.pojo.DTOAssembler;
import edu.com.softserveinc.bawl.models.CommentModel;
import edu.com.softserveinc.bawl.services.CommentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping(value = "comments")
public class CommentController {
    public static final String COMMENT_ADDED = "Success. Comment has been added";
    public static final String COMMENT_NOT_ADDED = "Failed. Comment hasn't been added";

    public static final Logger LOG = Logger.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "get", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<CommentDTO>> getCommentsByIssueId(@RequestParam("issueId") int id) {
        try {
            final List<CommentModel> commentsByIssueId = commentService.getCommentsByIssueId(id);
            final List<CommentDTO> commentsFrom = DTOAssembler.getCommentsFrom(commentsByIssueId);
            return new ResponseEntity<>(commentsFrom, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<CommentDTO> addComment(@RequestBody final CommentDTO comment) {
        try{
        CommentModel commentModel = new CommentModel()
                .withComment(comment.getComment())
                .withUserName(comment.getUserName())
                .withEmail(comment.getEmail())
                .withIssueId(comment.getIssueId());
            commentService.saveComment(commentModel);
            comment.withMessage(COMMENT_ADDED);
            return new ResponseEntity<>(DTOAssembler.getCommentFrom(commentModel).withMessage(COMMENT_ADDED), HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(comment.withMessage(COMMENT_NOT_ADDED), HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
