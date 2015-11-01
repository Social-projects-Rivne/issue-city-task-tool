package edu.com.softserveinc.bawl.controllers;

import edu.com.softserveinc.bawl.dto.CategoryDTO;
import edu.com.softserveinc.bawl.dto.DTOAssembler;
import edu.com.softserveinc.bawl.models.CategoryModel;
import edu.com.softserveinc.bawl.services.CategoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Controller for issue categories
 */
@Controller
@RequestMapping(value = "categories")
public class CategoryController {

    /**
     * Logger field
     */
    public static final Logger LOG = Logger.getLogger(CategoryController.class);

    public static final String MESSAGE_TEXT = "message";
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
     * @param message message
     * @return message
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addCategory(@RequestBody CategoryDTO category, Map<String, String> message) {
        try {
             service.addCategory(new CategoryModel(category.getName()));
             message.put(MESSAGE_TEXT, SUCCESS);
        } catch (Exception e) {
             message.put(MESSAGE_TEXT, FAILURE);
        }
        return message;
    }


}