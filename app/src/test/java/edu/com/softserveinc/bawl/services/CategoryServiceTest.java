package edu.com.softserveinc.bawl.services;

import edu.com.softserveinc.bawl.AbstractBawlTest;
import edu.com.softserveinc.bawl.dao.CategoryDao;
import edu.com.softserveinc.bawl.models.CategoryModel;
import edu.com.softserveinc.bawl.services.impl.CategoryServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.reflect.Whitebox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Created by alex on 21.10.15.
 */

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:test-root-context.xml", "classpath:test-data-context.xml","classpath:test-mail-context.xml"} )

public class CategoryServiceTest extends AbstractBawlTest {

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
        verify(categoryDao, times(1)).saveAndFlush(mockCategoryModel);
    }

    @Test
    public void deleteCategory_shouldChangeIsdeletedTo1AndSaveToDao() {
        CategoryModel testModel = new CategoryModel();
        categoryService.deleteCategory(testModel);
        verify(categoryDao, times(1)).saveAndFlush(testModel);
        assertEquals(1, testModel.getIsdeleted());
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
