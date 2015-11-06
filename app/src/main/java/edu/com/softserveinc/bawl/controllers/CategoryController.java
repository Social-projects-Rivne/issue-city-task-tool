package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.dto.CategoryDTO;
import edu.com.softserveinc.bawl.dto.DTOAssembler;
import edu.com.softserveinc.bawl.models.CategoryModel;
import edu.com.softserveinc.bawl.services.CategoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for issue categories
 */
@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    public static final Logger LOG = Logger.getLogger(CategoryController.class);

    public static final String SUCCESS = "Success. New category was added";
    public static final String FAILURE = "Failed. New category was not added";

    @Autowired
    private CategoryService service;

    /**
     * Returns list of the categories
     * @return list of the categories
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<CategoryDTO> getCategories() {
        return DTOAssembler.getCategoryDtoFrom(service.loadCategoriesList(), false);
    }

    /**
     * Creates new category
     * @param category new category
     * @return message
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CategoryDTO addCategory(@RequestBody CategoryDTO category) {
        try {
             service.addCategory(new CategoryModel(category.getName()));
             category.setMessage(SUCCESS);
        } catch (Exception e) {
             category.setMessage(FAILURE);
        }
        return category;
    }


}