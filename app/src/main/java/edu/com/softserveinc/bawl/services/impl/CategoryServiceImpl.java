package edu.com.softserveinc.bawl.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.com.softserveinc.bawl.dao.CategoryDao;
import edu.com.softserveinc.bawl.models.CategoryModel;
import edu.com.softserveinc.bawl.services.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
    private CategoryDao categoryDao;
	
	@Override
	public void addCategory(CategoryModel category) {
		categoryDao.saveAndFlush(category);
	}

	@Override
	public void deleteCategory(CategoryModel category) {
		categoryDao.delete(category);
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
