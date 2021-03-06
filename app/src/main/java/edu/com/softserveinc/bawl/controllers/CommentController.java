package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.dto.pojo.CommentDTO;
import edu.com.softserveinc.bawl.dto.pojo.DTOAssembler;
import edu.com.softserveinc.bawl.models.CommentModel;
import edu.com.softserveinc.bawl.services.CommentService;
import edu.com.softserveinc.bawl.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

import static edu.com.softserveinc.bawl.services.impl.MandrillMailServiceImpl.getMandrillMail;

@RestController
@RequestMapping(value = "comments")
public class CommentController {

  public static final String COMMENT_ADDED = "Success. Comment has been added";
  public static final String COMMENT_NOT_ADDED = "Failed. Comment hasn't been added";

  @Autowired
  private CommentService commentService;

  @Autowired
  private UserService userService;

  /**
   * Returns all issue comments
   * @param id issue id
   * @return list of comments
   */
  @RequestMapping(value = "get", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<List<CommentDTO>> getCommentsByIssueId(@RequestParam("issueId") int id) {
    try {
      final List<CommentModel> commentsByIssueId = commentService.getCommentsByIssueId(id);
      commentsByIssueId.forEach(comment -> {
        comment.setAvatar(userService.getAvatarByEmailOrDefault(comment.getEmail()));
      });
      final List<CommentDTO> commentsFrom = DTOAssembler.getCommentsFrom(commentsByIssueId);
      return new ResponseEntity<>(commentsFrom, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_ACCEPTABLE);
    }
  }

  /**
   * Adds new comment
   * @param comment comment to be added
   * @return new added comment
   */
  @RequestMapping(value = "add", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<CommentDTO> addComment(@RequestBody final CommentDTO comment) {
  //  SubscriptionService subscriptionService = new SubscriptionServiceImpl();

    try {
      CommentModel commentModel =
          new CommentModel().withComment(comment.getComment()).withUserName(comment.getUserName())
              .withEmail(comment.getEmail()).withIssueId(comment.getIssueId());
      commentModel = commentService.saveComment(commentModel);
      comment.withMessage(COMMENT_ADDED);
      getMandrillMail().sendCommentNotiffication(commentModel.getComment(), commentModel.getIssueId());
      return new ResponseEntity<>(DTOAssembler.getCommentFrom(commentModel).withMessage(COMMENT_ADDED), HttpStatus.OK);
    } catch (Exception ex) {
      return new ResponseEntity<>(comment.withMessage(COMMENT_NOT_ADDED), HttpStatus.NOT_ACCEPTABLE);
    }
  }
}
