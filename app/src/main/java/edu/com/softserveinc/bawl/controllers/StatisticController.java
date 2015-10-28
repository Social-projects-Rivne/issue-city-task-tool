package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.models.CategoryModel;
import edu.com.softserveinc.bawl.models.IssueModel;
import edu.com.softserveinc.bawl.models.IssueStatus;
import edu.com.softserveinc.bawl.services.CategoryService;
import edu.com.softserveinc.bawl.services.CommentService;
import edu.com.softserveinc.bawl.services.HistoryService;
import edu.com.softserveinc.bawl.services.IssueService;
import edu.com.softserveinc.bawl.services.StatusService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Autowired
	private HistoryService historyService;
	
	@RequestMapping(value = "statistic-by-categories", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, String>> statisticByCategory() {
		List<Map<String, String>> statistic = new ArrayList<Map<String, String>>();
		List<IssueModel> issues = historyService.getLastUniqueIssues();
		List<CategoryModel> categories = categoryService.loadCategoriesList();

		for (CategoryModel categoryModel : categories) {
			final HashMap<String, String> e = new HashMap<>();
			e.put("label", categoryModel.getName());
			e.put("value", "" + categoryModel.getIssues().size());

		}
		return statistic;
	}
	
	@RequestMapping(value = "statistic-by-statuses", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, String>> statisticByStatus() {
		List<Map<String, String>> statistic = new ArrayList<Map<String, String>>();
		List<IssueModel> issues = historyService.getLastUniqueIssues();
		IssueStatus[] statuses = IssueStatus.values();
		
		for (int i = 0, tmp; i < statuses.length; i++) {
			tmp = 0;

			for (int j = 0; j < issues.size(); j++) {
				if (issues.get(j).getStatus() == statuses[i]) {
					tmp++;
				}
			}
			
			statistic.add(new HashMap<String, String>());
			statistic.get(i).put("label", statuses[i].name());
			statistic.get(i).put("value", "" + tmp);
		}
		
		return statistic;
	}
	
	@RequestMapping(value = "statistic-by-comments", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, String>> statisticByComments() {
		List<Map<String, String>> statistic = new ArrayList<Map<String, String>>();
		List<IssueModel> issues = historyService.getLastUniqueIssues();
		
		for (int i = 0; i < issues.size(); i++) {
			
			statistic.add(new HashMap<String, String>());
			statistic.get(i).put("label", issues.get(i).getName());
			statistic.get(i).put("value", "" + commentService.getCommentsByIssueId(issues.get(i).getId()).size());
			
		}
		
		return statistic;
	}
	
}

