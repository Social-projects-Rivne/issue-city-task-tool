package edu.com.softserveinc.main.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.com.softserveinc.main.dao.DaoImpl;
import edu.com.softserveinc.main.models.CommentModel;
import edu.com.softserveinc.main.models.UserModel;
import edu.com.softserveinc.main.services.CommentServiceImpl;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/cont")
public class TestPageController {

	@RequestMapping(value = "/testPage", method = RequestMethod.GET)
	public String testPage() {
		return "testPage";
	}

	@RequestMapping(value = "all-comments/{id}", method = RequestMethod.GET)
	@ResponseBody
	public List getAllByIssueId(@PathVariable int id) {
		return new DaoImpl().getAll(new CommentModel());
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "testPage", method = RequestMethod.POST)
	public @ResponseBody java.util.LinkedHashMap addComment(
			@RequestBody final java.util.LinkedHashMap comment) {
		int id = Integer.parseInt(comment.get("issueId").toString());
		new CommentServiceImpl().addComment(new CommentModel(comment.get(
				"comment").toString(), comment.get("userName").toString(),
				comment.get("email").toString(), id));
		System.out.println("email: " + comment.get("email") + "issue id: "
				+ comment.get("issueId"));
		return comment;
	}

}
// Test
/*
 * @RequestMapping(value = "testPage", method = RequestMethod.POST, consumes = {
 * "application/json;charset=UTF-8" }, produces = {
 * "application/json;charset=UTF-8" }) public @ResponseBody CommentModel
 * addComment(
 * 
 * @RequestBody final CommentModel comment) { new
 * CommentServiceImpl().addComment(comment);
 * System.out.println("added in DB comment: " + comment.getId()); return
 * comment; }
 * 
 * @RequestMapping(value = "testPage", method = RequestMethod.PUT) public
 * 
 * @ResponseBody IssueModel addComment(
 * 
 * @RequestBody final IssueModel comment, int id) { new
 * IssueServiceImpl().addProblemm(comment);
 * System.out.println("added in DB comment: " + comment.getDescription());
 * return comment; }
 * 
 * /*
 * 
 * @RequestMapping(value = "testPage", method = RequestMethod.POST) public
 * 
 * @ResponseBody IssueModel addComment(@RequestBody final IssueModel comment) {
 * new IssueServiceImpl().addProblemm(comment);
 * System.out.println("added in DB comment: " + comment.getDescription());
 * return comment; }
 * 
 * /*
 * 
 * @SuppressWarnings("rawtypes")
 * 
 * @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
 * 
 * @ResponseBody public IssueModel getById(@PathVariable int id) {
 * 
 * return new IssueServiceImpl().getByID(id); }
 * 
 * @RequestMapping(value = "comment/{id}", method = RequestMethod.GET)
 * 
 * @ResponseBody public CommentModel getCommentById(@PathVariable int id) {
 * 
 * return new CommentModel("bla bla bla", "supermen", "m@m.c", 1); } }
 * 
 * /*
 * 
 * @RequestMapping(value = "add-comment", method = RequestMethod.POST) public
 * 
 * @ResponseBody CommentModel addComment(@RequestBody final CommentModel
 * comment) {
 * 
 * new CommentServiceImpl().addComment(new CommentModel("asdads", "asa",
 * "ss@ss.s", 1));
 * 
 * new CommentServiceImpl().addComment(comment); System.out.println("comment: "
 * + comment.getComment()); return comment; }
 * 
 * 
 * @RequestMapping(value = "person", method = RequestMethod.POST) public
 * 
 * @ResponseBody UserModel post(@RequestBody final UserModel user) {
 * 
 * System.out.println(user.getId() + " " + user.getName()); return user; }
 */
/*
 * @RequestMapping(value = "add-comment", method = RequestMethod.POST) public
 * 
 * @ResponseBody CommentModel addComment(@RequestBody final CommentModel
 * comment) {
 * 
 * new CommentServiceImpl().addComment(new CommentModel("asdads", "asa",
 * "ss@ss.s", 1));
 * 
 * new CommentServiceImpl().addComment(comment); System.out.println("comment: "
 * + comment.getComment()); return comment; }
 * 
 * 
 * @RequestMapping(value = "person", method = RequestMethod.POST) public
 * 
 * @ResponseBody UserModel post(@RequestBody final UserModel user) {
 * 
 * System.out.println(user.getId() + " " + user.getName()); return user; }
 */
