package edu.com.softserveinc.bawl.services.impl;

import edu.com.softserveinc.bawl.dao.HistoryDao;
import edu.com.softserveinc.bawl.dao.IssueDao;
import edu.com.softserveinc.bawl.models.CategoryModel;
import edu.com.softserveinc.bawl.models.HistoryModel;
import edu.com.softserveinc.bawl.models.IssueModel;
import edu.com.softserveinc.bawl.models.UserModel;
import edu.com.softserveinc.bawl.models.enums.IssueStatus;
import edu.com.softserveinc.bawl.services.CategoryService;
import edu.com.softserveinc.bawl.services.IssueService;
import edu.com.softserveinc.bawl.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static edu.com.softserveinc.bawl.models.enums.IssueStatusHelper.getIssueStatusForAddIssue;

@Service
@Transactional
public class IssueServiceImpl implements IssueService {

  public static final Logger LOG = Logger.getLogger(IssueServiceImpl.class);

  @Autowired
  private IssueDao issueDao;

  @Autowired
  private HistoryDao historyDao;

  @Autowired
  private CategoryService categoryService;

  @Autowired
  private UserService userService;

  @Override
  public IssueModel addIssue(String name, String description, String mapPointer, String attachments, String category,
      int priorityId) {
    Optional<CategoryModel> categoryModel = categoryService.getCategoryByNameOrAddNew(category);
    IssueModel issue =
        new IssueModel(name, description, mapPointer, attachments, categoryModel.get(), priorityId, IssueStatus.NEW);
    UserModel userModel = userService.getCurrentUser();
    issue.setStatus(getIssueStatusForAddIssue(userModel.getRole()));
    return addProblem(issue, userModel.getId());
  }
  @Override
  public IssueModel addProblem(IssueModel problem, int userId) {
    IssueModel issueModel = issueDao.saveAndFlush(problem);
    saveToHistory(problem, userId);
    return issueModel;
  }

  @Override
  public void editProblem(IssueModel problem, int userId) {
    issueDao.saveAndFlush(problem);
    saveToHistory(problem, userId);
  }

  @Override
  public void deleteProblem(IssueModel problem, int userId) {
    problem.setStatus(IssueStatus.DELETED);
    saveToHistory(problem, userId);
  }

  @Override
  public void deleteProblem(int issueId, int userId) {
    IssueModel issueModel = issueDao.findOne(issueId);
    issueModel.setStatus(IssueStatus.DELETED);
    saveToHistory(issueModel, userId);
  }

  /**
   * Change category in Issues, which has has been removed.
   * Sets categort in Issues to otherCategory (garbage category)
   *
   * @param categoryId    category Id
   * @param otherCategory
   */
  @Override
  public void onCategoryDelete(int categoryId, CategoryModel otherCategory) {
    //TODO do this by single batch db update
    List<IssueModel> issueModels = issueDao.findByCategoryId(categoryId);
    for (IssueModel issueModel : issueModels) {
      issueModel.setCategory(otherCategory);
    }
    issueDao.save(issueModels);
    issueDao.flush();
  }

  @Override
  public List<IssueModel> loadIssuesList() {
    return issueDao.findAll();
  }

  @Override
  public Optional<IssueModel> getById(int issueId) {
    return Optional.ofNullable(issueDao.findOne(issueId));
  }


  private void saveToHistory(IssueModel problem, int userId) {
    HistoryModel historyModel = new HistoryModel();
    historyModel.setStatus(problem.getStatus());
    historyModel.setIssue(problem);
    historyModel.setUserId(userId);
    historyModel.setDate(new Date());
    historyDao.saveAndFlush(historyModel);
  }

}
