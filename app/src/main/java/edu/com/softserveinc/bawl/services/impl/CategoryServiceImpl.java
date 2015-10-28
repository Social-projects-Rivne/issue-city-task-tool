package edu.com.softserveinc.bawl.services.impl;

import edu.com.softserveinc.bawl.dao.CategoryDao;
import edu.com.softserveinc.bawl.models.CategoryModel;
import edu.com.softserveinc.bawl.services.CategoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static edu.com.softserveinc.bawl.models.CategoryState.DELETED;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	/**
     *  Logger field
     */
    public static final Logger LOG=Logger.getLogger(CategoryServiceImpl.class);
	
	@Autowired
    private CategoryDao categoryDao;
	
	@Override
	public CategoryModel addCategory(CategoryModel category) {
        return categoryDao.saveAndFlush(category);
    }

	@Override
	public void deleteCategory(CategoryModel category) {
		category.setState(DELETED);
		categoryDao.saveAndFlush(category);
	}

	@Override
	public void editCategory(CategoryModel category) {
		categoryDao.saveAndFlush(category);
	}

	@Override
	public CategoryModel getCategoryByID(int id) {
		return categoryDao.findOne(id);
	}

	@Override
	public List<CategoryModel> loadCategoriesList() {
		return categoryDao.findAll();
	}

	@Override
	public CategoryModel getCategoryByName(String name){
		return categoryDao.findByName(name);
	}
}
