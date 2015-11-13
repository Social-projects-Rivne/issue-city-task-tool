package edu.com.softserveinc.bawl.services;

import edu.com.softserveinc.bawl.AbstractBawlFunctionalTest;
import edu.com.softserveinc.bawl.dao.CategoryDao;
import edu.com.softserveinc.bawl.models.CategoryModel;
import edu.com.softserveinc.bawl.models.enums.CategoryState;
import edu.com.softserveinc.bawl.services.impl.CategoryServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CategoryServiceTest extends AbstractBawlFunctionalTest {

    private CategoryService categoryService = null;
    private CategoryDao categoryDao;

    @Before
    public void setup() {
        categoryService = new CategoryServiceImpl();
        categoryDao = mock(CategoryDao.class);
        Whitebox.setInternalState(categoryService, "categoryDao", categoryDao);
    }

    @Test
    public void addCategory_shouldCallAddCategoryToDao() {
        CategoryModel mockCategoryModel = mock(CategoryModel.class);
        categoryService.addCategory(mockCategoryModel);
        verify(categoryDao, times(1)).saveAndFlush(any(CategoryModel.class));
    }

    @Test
    public void deleteCategory_shouldChangeIsdeletedTo1AndSaveToDao() {
        CategoryModel testModel = new CategoryModel();
        categoryService.deleteCategory(testModel);
        verify(categoryDao, times(1)).saveAndFlush(testModel);
        assertEquals(CategoryState.DELETED, testModel.getState());
    }

    @Test
    public void editCategory_shouldCallAddCategoryToDao() {
        CategoryModel mockCategoryModel = mock(CategoryModel.class);
        categoryService.editCategory(mockCategoryModel);
        verify(categoryDao, times(1)).saveAndFlush(mockCategoryModel);
    }

    @Test
    public void getCategoryByID_shouldReturnCategoryByConcreteID() {
        int categoryID = 2;
        CategoryModel testModel = new CategoryModel();
        testModel.setId(categoryID);
        assertEquals(categoryID, testModel.getId());
    }

    @Test
    public void getCategoryByName_shouldReturnCategoryByConcreteName() {
        String categoryName = "Test name";
        CategoryModel testModel = new CategoryModel("Test name");
        assertEquals(categoryName, testModel.getName());
    }

    @Test
    public void loadCategoryList_shouldReturnListOfAllCategories() {
        categoryService.loadCategoriesList();
        verify(categoryDao, times(1)).findAll();
    }
}
