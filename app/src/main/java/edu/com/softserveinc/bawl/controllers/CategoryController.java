package edu.com.softserveinc.bawl.controllers;

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
public class CategoryController {

    /**
     * Logger field
     */
    public static final Logger LOG = Logger.getLogger(CategoryController.class);

    @Autowired
    private CategoryService service;

    @RequestMapping("get-categories")
    @ResponseBody
    public List<CategoryModel> getCategories() {
        return service.loadCategoriesList();
    }

    @RequestMapping(value = "category", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addCategory(@RequestBody CategoryModel category, Map<String, String> message) {
        try {
             service.addCategory(category);
             message.put("message", "New category was successfully added");
        } catch (Exception e) {
             message.put("message", "Some problem occured! New category was not added");
        }
        return message;
    }


}