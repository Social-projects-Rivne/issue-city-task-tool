package edu.com.softserveinc.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.com.softserveinc.main.models.CommentModel;
import edu.com.softserveinc.main.services.CommentService;


@Controller
public class CommentController {
	
	@Autowired
	private CommentService service;
	
	
	@RequestMapping("get-comments")
	public @ResponseBody List<CommentModel> getCommentsByIssueId(@RequestParam("issueId") int id) {
		return service.getCommentsByIssueId(id);
	}


	@RequestMapping(value = "all-comments/{id}", method = RequestMethod.GET)
	@ResponseBody
	@PreAuthorize("hasRole('ROLE_ADMIN')")
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
