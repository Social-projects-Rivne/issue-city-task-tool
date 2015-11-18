package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.dto.pojo.CategoryDTO;
import edu.com.softserveinc.bawl.dto.pojo.DTOAssembler;
import edu.com.softserveinc.bawl.dto.pojo.ResponseDTO;
import edu.com.softserveinc.bawl.models.CategoryModel;
import edu.com.softserveinc.bawl.services.CategoryService;
import edu.com.softserveinc.bawl.services.IssueService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public static final String FAILURE_UPDATEDD = "Failed. New category hasn't been updated";
    public static final String SUCCESS_DELETE = "Category has been deleted.";
    public static final String FAILURE_DELETE = "Failed. Category hasn't been deleted.";

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private IssueService issueService;

    /**
     * Returns list of the categories
     * @return list of the categories
     */
    @RequestMapping(value = "all",method = RequestMethod.GET)
    @ResponseBody
    public List<CategoryDTO> getCategories() {
        return DTOAssembler.getCategoryDtoFrom(categoryService.loadCategoriesList(), false);
    }

    /**
     * Creates new category
     * @param category new category
     * @return message
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public CategoryDTO addCategory(@RequestBody CategoryDTO category) {
        try {
             categoryService.addCategory(new CategoryModel(category.getName()));
             category.setMessage(SUCCESS_ADDED);
        } catch (Exception e) {
             category.setMessage(FAILURE_ADDED);
        }
        return category;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public @ResponseBody
    CategoryDTO editCategory(@RequestBody CategoryDTO categoryDTO) {
        try {
            categoryService.updateCategory(categoryDTO.getId(), categoryDTO.getName(), getState(categoryDTO.getState()));
            categoryDTO.setMessage(SUCCESS_UPDATED);
        } catch (Exception ex) {
            categoryDTO.setMessage(FAILURE_UPDATEDD);
        }

        return categoryDTO;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseDTO removeCategory(@PathVariable int id) {
        ResponseDTO responseDTO = new ResponseDTO();
        try{
            categoryService.deleteCategory(id);
            issueService.onCategoryDelete(id, categoryService.getOtherCategory());
            responseDTO.setMessage(SUCCESS_DELETE);
        } catch (Exception ex) {
            responseDTO.setMessage(FAILURE_DELETE);
        }
        return responseDTO;
    }


}