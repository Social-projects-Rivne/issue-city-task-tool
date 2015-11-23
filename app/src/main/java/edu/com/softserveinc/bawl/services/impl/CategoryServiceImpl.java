package edu.com.softserveinc.bawl.services.impl;

import edu.com.softserveinc.bawl.dao.CategoryDao;
import edu.com.softserveinc.bawl.models.CategoryModel;
import edu.com.softserveinc.bawl.models.enums.CategoryState;
import edu.com.softserveinc.bawl.services.CategoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

import static edu.com.softserveinc.bawl.models.enums.CategoryState.DELETED;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

  public static final Logger LOG = Logger.getLogger(CategoryServiceImpl.class);
  public static final String OTHER_CATEGORY = "other";

  @Autowired
  private CategoryDao categoryDao;

  @Override
  public CategoryModel addCategory(CategoryModel category) {
    return categoryDao.saveAndFlush(category);
  }

  @Override
  public CategoryModel addCategory(String category) {
    return categoryDao.saveAndFlush(new CategoryModel(category));
  }

  @Override
  public void deleteCategory(int id) {
    CategoryModel categoryModel = getCategoryByID(id);
    categoryModel.setState(DELETED);
    categoryDao.saveAndFlush(categoryModel);
  }

  @Override
  public void deleteCategory(CategoryModel category) {
    category.setState(DELETED);
    categoryDao.saveAndFlush(category);
  }

  @Override
  public void updateCategory(int id, String name, CategoryState state) {
    CategoryModel categoryModel = getCategoryByID(id);
    categoryModel.setName(name);
    categoryModel.setState(state);
    categoryDao.saveAndFlush(categoryModel);
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
  public Optional<CategoryModel> getCategoryByName(String name) {
    return Optional.ofNullable(categoryDao.findByName(name));
  }

  @Override
  public Optional<CategoryModel> getCategoryByNameOrAddNew(String name) {
    if (StringUtils.isEmpty(name)) {
      return Optional.empty();
    }
    return Optional.ofNullable(getCategoryByName(name.toLowerCase()).orElse(addCategory(name)));

  }

  @Override
  public CategoryModel getOtherCategory() {
    CategoryModel category = categoryDao.findByName(OTHER_CATEGORY);
    if (category == null) {
      category = addCategory(OTHER_CATEGORY);
    }
    return category;
  }

}
