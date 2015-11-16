package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.dto.pojo.ResponseDTO;
import edu.com.softserveinc.bawl.models.CategoryModel;
import edu.com.softserveinc.bawl.models.IssueModel;
import edu.com.softserveinc.bawl.models.enums.IssueStatus;
import edu.com.softserveinc.bawl.services.CategoryService;
import edu.com.softserveinc.bawl.services.CommentService;
import edu.com.softserveinc.bawl.services.HistoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static edu.com.softserveinc.bawl.dto.pojo.DTOAssembler.getResponseDTOs;

@RestController
@RequestMapping(value = "statistics")
public class StatisticController {

	public static final Logger LOG=Logger.getLogger(StatisticController.class);
	
	@Autowired
	private CategoryService categoryService;

	
	@Autowired
	private CommentService commentService;

	@Autowired
	private HistoryService historyService;
	
	@RequestMapping(value = "categories", method = RequestMethod.POST)
	@ResponseBody
	public List<ResponseDTO> statisticByCategory() {
		List<CategoryModel> categories = categoryService.loadCategoriesList();
		List<ResponseDTO> responseDTOs = new ArrayList<>();

		for (CategoryModel categoryModel : categories) {
			ResponseDTO responseDTO = new ResponseDTO();
			responseDTO.setLabel(categoryModel.getName());
			responseDTO.setValue(String.valueOf(categoryModel.getIssues().size()));
			responseDTOs.add(responseDTO);
		}

		return responseDTOs;
	}
	
	@RequestMapping(value = "statuses", method = RequestMethod.POST)
	@ResponseBody
	public List<ResponseDTO> statisticByStatus() {
		List<IssueModel> issues = historyService.getLastUniqueIssues();
		List<IssueStatus> statuses = Arrays.asList(IssueStatus.values());
		List<ResponseDTO> responseDTOs = new ArrayList<>();
		// TODO rewrite this
		for (int i = 0, tmp; i < statuses.size(); i++) {
			tmp = 0;

			for (int j = 0; j < issues.size(); j++) {
				if (issues.get(j).getStatus().equals(statuses.get(i))) {
					tmp++;
				}
			}
			ResponseDTO responseDTO = new ResponseDTO();
			responseDTO.setLabel(statuses.get(i).name());
			responseDTO.setValue(String.valueOf(tmp));
			responseDTOs.add(responseDTO);
		}
		
		return responseDTOs;
	}
	
	@RequestMapping(value = "comments", method = RequestMethod.POST)
	@ResponseBody
	public List<ResponseDTO> statisticByComments() {
		List<IssueModel> issues = historyService.getLastUniqueIssues();
		List<ResponseDTO> responseDTOs = getResponseDTOs(issues, commentService);
		
		return responseDTOs;
	}



}

