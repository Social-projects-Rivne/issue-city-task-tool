package edu.com.softserveinc.bawl.services.impl;

import edu.com.softserveinc.bawl.dao.CategoryDao;
import edu.com.softserveinc.bawl.dto.CategoryDTO;
import edu.com.softserveinc.bawl.dto.DTOAssembler;
import edu.com.softserveinc.bawl.models.CategoryModel;
import edu.com.softserveinc.bawl.services.CategoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static edu.com.softserveinc.bawl.models.enums.CategoryState.DELETED;

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
	public CategoryModel addCategory(CategoryDTO category) {
		return addCategory(category.getName());
    }

	@Override
	public CategoryModel addCategory(String categoryName) {
		final CategoryModel categoryModel = new CategoryModel(categoryName);
		return categoryDao.saveAndFlush(categoryModel);
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
	public List<CategoryDTO> loadCategoriesWithoutIssues() {
		return DTOAssembler.getCategoryDtoFrom(categoryDao.findAll(), false);
	}

	@Override
    public List<CategoryDTO> loadCategoriesListWithIssues() {
		return DTOAssembler.getCategoryDtoFrom(categoryDao.findAll(), true);
	}

	@Override
	public CategoryModel getCategoryByName(String name){
		return categoryDao.findByName(name);
	}
}
