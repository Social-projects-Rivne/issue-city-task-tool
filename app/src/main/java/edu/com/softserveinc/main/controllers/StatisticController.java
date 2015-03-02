package edu.com.softserveinc.main.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.com.softserveinc.main.models.CategoryModel;
import edu.com.softserveinc.main.models.IssueModel;
import edu.com.softserveinc.main.models.StatusModel;
import edu.com.softserveinc.main.services.CategoryService;
import edu.com.softserveinc.main.services.CommentService;
import edu.com.softserveinc.main.services.IssueService;
import edu.com.softserveinc.main.services.StatusService;

@Controller
public class StatisticController {
	
	@Autowired
	private IssueService issueService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private StatusService statusService;
	
	@Autowired
	private CommentService commentService;
	
	@RequestMapping(value = "statistic-by-category", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> statisticByCategory() {
		Map<String, String> statistic = new HashMap<String, String>();
		List<IssueModel> issues = issueService.loadIsssueList();
		List<CategoryModel> categories = categoryService.loadCategoriesList();
		
		for (int i = 0, tmp; i < categories.size(); i++) {
			tmp = 0;
			
			for (int j = 0; j < issues.size(); j++) {
				if (issues.get(j).getCategoryId() == categories.get(i).getId()) {
					tmp++;
				}	
			}
			
			statistic.put(categories.get(i).getName(), "" + tmp);
		}
		
		return statistic;
	}
	
	@RequestMapping(value = "statistic-by-status", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> statisticByStatus() {
		Map<String, String> statistic = new HashMap<String, String>();
		List<IssueModel> issues = issueService.loadIsssueList();
		List<StatusModel> statuses = statusService.loadStatusList();
		
		for (int i = 0, tmp; i < statuses.size(); i++) {
			tmp = 0;
			
			for (int j = 0; j < issues.size(); j++) {
				if (issues.get(j).getStatusId() == statuses.get(i).getId()) {
					tmp++;
				}	
			}
			
			statistic.put(statuses.get(i).getName(), "" + tmp);
		}
		
		return statistic;
	}
	
}

