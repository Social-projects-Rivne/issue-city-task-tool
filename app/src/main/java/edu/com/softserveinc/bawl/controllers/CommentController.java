package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.dto.CommentDTO;
import edu.com.softserveinc.bawl.dto.DTOAssembler;
import edu.com.softserveinc.bawl.models.CommentModel;
import edu.com.softserveinc.bawl.services.CommentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "comments")
public class CommentController {

	public static final Logger LOG=Logger.getLogger(CommentController.class);
	
	@Autowired
	private CommentService service;

	@RequestMapping("get")
	public  @ResponseBody List<CommentDTO> getCommentsByIssueId(@RequestParam("issueId") int id) {
		return DTOAssembler.getCommentsFrom(service.getCommentsByIssueId(id));
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public  @ResponseBody CommentDTO addCommentAction(@RequestBody final CommentDTO comment) {
		final CommentModel commentModel = new CommentModel(comment.getComment(), comment.getUserName(), comment.getEmail(), comment.getIssueId());
		service.addComment(commentModel);
		return comment;
	}
		
}
