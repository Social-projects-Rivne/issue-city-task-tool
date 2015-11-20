package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.dto.pojo.CategoryDTO;
import edu.com.softserveinc.bawl.dto.pojo.DTOAssembler;
import edu.com.softserveinc.bawl.dto.pojo.ResponseDTO;
import edu.com.softserveinc.bawl.models.CategoryModel;
import edu.com.softserveinc.bawl.services.CategoryService;
import edu.com.softserveinc.bawl.services.IssueService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static edu.com.softserveinc.bawl.models.enums.CategoryState.getState;

/**
 * Controller for issue categories
 */
@RestController
@RequestMapping(value = "category")
public class CategoryController {

  public static final Logger LOG = Logger.getLogger(CategoryController.class);

  public static final String SUCCESS_ADDED = "Success. New category has been added";
  public static final String FAILURE_ADDED = "Failed. New category hasn't been added";
  public static final String SUCCESS_UPDATED = "Success. Category has been updated";
  public static final String FAILURE_UPDATED = "Failed. New category hasn't been updated";
  public static final String SUCCESS_DELETE = "Category has been deleted.";
  public static final String FAILURE_DELETE = "Failed. Category hasn't been deleted.";

  @Autowired
  private CategoryService categoryService;

  @Autowired
  private IssueService issueService;

  /**
   * Returns list of the categories
   *
   * @return list of the categories
   */
  @RequestMapping(value = "all", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<List<CategoryDTO>> getCategories() {
    final List<CategoryModel> categoryModels = categoryService.loadCategoriesList();
    final List<CategoryDTO> categoryDtoFrom =
        DTOAssembler.getCategoryDtoFrom(categoryModels, DTOAssembler.DoMapIssues.NO);
    return new ResponseEntity<>(categoryDtoFrom, HttpStatus.OK);
  }

  /**
   * Creates new category
   *
   * @param category new category
   * @return message
   */
  @RequestMapping(method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO category) {
    try {
      categoryService.addCategory(new CategoryModel(category.getName()));
      return new ResponseEntity<>(category.withMessage(SUCCESS_ADDED), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(category.withMessage(FAILURE_ADDED), HttpStatus.NOT_ACCEPTABLE);
    }
  }

  @RequestMapping(value = "{id}", method = RequestMethod.PUT)
  @ResponseBody
  public ResponseEntity<CategoryDTO> editCategory(@RequestBody CategoryDTO category) {
    try {
      categoryService.updateCategory(category.getId(), category.getName(), getState(category.getState()));
      return new ResponseEntity<>(category.withMessage(SUCCESS_UPDATED), HttpStatus.OK);
    } catch (Exception ex) {
      return new ResponseEntity<>(category.withMessage(FAILURE_UPDATED), HttpStatus.NOT_ACCEPTABLE);

    }
  }


  @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
  @ResponseBody
  public ResponseEntity<CategoryDTO> removeCategory(@PathVariable int id) {
    CategoryDTO responseDTO = new CategoryDTO();
    try {
      categoryService.deleteCategory(id);
      issueService.onCategoryDelete(id, categoryService.getOtherCategory());
      return new ResponseEntity<>(responseDTO.withMessage(SUCCESS_DELETE), HttpStatus.OK);
    } catch (Exception ex) {
      return new ResponseEntity<>(responseDTO.withMessage(FAILURE_DELETE), HttpStatus.NOT_ACCEPTABLE);
    }
  }


}