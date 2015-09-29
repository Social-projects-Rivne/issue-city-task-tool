package edu.com.softserveinc.bawl.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.com.softserveinc.bawl.models.CategoryModel;
import edu.com.softserveinc.bawl.models.IssueModel;
import edu.com.softserveinc.bawl.models.StatusModel;
import edu.com.softserveinc.bawl.services.CategoryService;
import edu.com.softserveinc.bawl.services.CommentService;
import edu.com.softserveinc.bawl.services.IssueService;
import edu.com.softserveinc.bawl.services.StatusService;

import org.apache.log4j.Logger;

@Controller
public class StatisticController {

	/**
	 * Logger field
	 */
	public static final Logger LOG=Logger.getLogger(StatisticController.class);
	
	@Autowired
	private IssueService issueService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private StatusService statusService;
	
	@Autowired
	private CommentService commentService;
	
	@RequestMapping(value = "statistic-by-categories", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, String>> statisticByCategory() {
		List<Map<String, String>> statistic = new ArrayList<Map<String, String>>();
		List<IssueModel> issues = issueService.loadIsssueList();
		List<CategoryModel> categories = categoryService.loadCategoriesList();
		
		for (int i = 0, tmp; i < categories.size(); i++) {
			tmp = 0;
			
			for (int j = 0; j < issues.size(); j++) {
				if (issues.get(j).getCategoryId() == categories.get(i).getId()) {
					tmp++;
				}	
			}
			
			statistic.add(new HashMap<String, String>());
			statistic.get(i).put("label", categories.get(i).getName());
			statistic.get(i).put("value", "" + tmp);
		}
		
		return statistic;
	}
	
	@RequestMapping(value = "statistic-by-statuses", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, String>> statisticByStatus() {
		List<Map<String, String>> statistic = new ArrayList<Map<String, String>>();
		List<IssueModel> issues = issueService.loadIsssueList();
		List<StatusModel> statuses = statusService.loadStatusList();
		
		for (int i = 0, tmp; i < statuses.size(); i++) {
			tmp = 0;
			
			for (int j = 0; j < issues.size(); j++) {
				if (issues.get(j).getStatusId() == statuses.get(i).getId()) {
					tmp++;
				}	
			}
			
			statistic.add(new HashMap<String, String>());
			statistic.get(i).put("label", statuses.get(i).getName());
			statistic.get(i).put("value", "" + tmp);
		}
		
		return statistic;
	}
	
	@RequestMapping(value = "statistic-by-comments", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, String>> statisticByComments() {
		List<Map<String, String>> statistic = new ArrayList<Map<String, String>>();
		List<IssueModel> issues = issueService.loadIsssueList();
		
		for (int i = 0; i < issues.size(); i++) {
			
			statistic.add(new HashMap<String, String>());
			statistic.get(i).put("label", issues.get(i).getName());
			statistic.get(i).put("value", "" + commentService.getCommentsByIssueId(issues.get(i).getId()).size());
			
		}
		
		return statistic;
	}
	
}

