package edu.com.softserveinc.bawl.services;

import edu.com.softserveinc.bawl.models.CategoryModel;
import edu.com.softserveinc.bawl.models.enums.CategoryState;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

  CategoryModel addCategory(CategoryModel category);

  Optional<CategoryModel> addCategory(String category);

  void deleteCategory(int id);

  void deleteCategory(CategoryModel category);

  void updateCategory(int id, String name, CategoryState state);

  Optional<CategoryModel> getCategoryByID(int id);

  List<CategoryModel> loadCategoriesList();

  Optional<CategoryModel> getCategoryByName(String name);

  Optional<CategoryModel> getCategoryByNameOrAddNew(String name);

  CategoryModel getOtherCategory();
}
