package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.models.CommentModel;
import edu.com.softserveinc.bawl.services.CommentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class CommentController {

	/**
	 * Logger field
	 */
	public static final Logger LOG=Logger.getLogger(CommentController.class);
	
	@Autowired
	private CommentService service;
	
	
	@RequestMapping("get-comments")
	public @ResponseBody List<CommentModel> getCommentsByIssueId(@RequestParam("issueId") int id) {
		return service.getCommentsByIssueId(id);
	}


	@RequestMapping(value = "all-comments/{id}", method = RequestMethod.GET)
	@ResponseBody
	public List<CommentModel> getAllCommentsByIssueId(@PathVariable int id) {
		return service.getCommentsByIssueId(id);
	}
	

	// adding comment for issue
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "add-comment", method = RequestMethod.POST)
	public @ResponseBody java.util.LinkedHashMap addCommentAction(
			@RequestBody final java.util.LinkedHashMap comment) {
		int id = Integer.parseInt(comment.get("issueId").toString());
		service.addComment(new CommentModel(comment.get(
				"comment").toString(), comment.get("userName").toString(),
				comment.get("email").toString(), id));
		System.out.println("email: " + comment.get("email") + "issue id: " + comment.get("issueId"));
		return comment;
	}
		
}
