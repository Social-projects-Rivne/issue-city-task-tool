package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.dto.pojo.CategoryDTO;
import edu.com.softserveinc.bawl.dto.pojo.ResponseDTO;
import edu.com.softserveinc.bawl.dto.pojo.StatisticDTO;
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

/**
 * Controller for statistic lookup
 */
@RestController
@RequestMapping(value = "statistics")
public class StatisticController {

  public static final Logger LOG = Logger.getLogger(StatisticController.class);

  @Autowired
  private CategoryService categoryService;

  @Autowired
  private CommentService commentService;

  @Autowired
  private HistoryService historyService;

  /**
   * Returns statistic by issue categories
   * @return
   */
  @RequestMapping(value = "categories", method = RequestMethod.POST)
  @ResponseBody
  public List<CategoryDTO> statisticByCategory() {
    List<CategoryDTO> categories = new ArrayList<>();
    categoryService.loadCategoriesList().forEach(category -> {
      categories
          .add(new CategoryDTO().withLabel(category.getName()).withValue(String.valueOf(category.getIssues().size())));
    });
    return categories;
  }

  /**
   * Returns statistic by issue statuses
   * @return
   */
  @RequestMapping(value = "statuses", method = RequestMethod.POST)
  @ResponseBody
  public List<ResponseDTO> statisticByStatus() {
    List<ResponseDTO> responseDTOs = new ArrayList<>();
    List<IssueModel> issues = historyService.getLastUniqueIssues();
    Arrays.asList(IssueStatus.values()).forEach(issueStatus -> {
      final long count = issues.parallelStream().filter(issue -> issue.getStatus() == issueStatus).count();
      responseDTOs.add(new StatisticDTO().withLabel(issueStatus.name()).withValue(count));
    });
    return responseDTOs;
  }

  /**
   * Returns statistic by comments
   * @return
   */
  @RequestMapping(value = "comments", method = RequestMethod.POST)
  @ResponseBody
  public List<ResponseDTO> statisticByComments() {
    return getResponseDTOs(historyService.getLastUniqueIssues(), commentService);
  }



}

