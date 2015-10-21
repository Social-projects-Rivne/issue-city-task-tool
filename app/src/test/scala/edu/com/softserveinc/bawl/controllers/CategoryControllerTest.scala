package edu.com.softserveinc.bawl.controllers

import java.util

import edu.com.softserveinc.bawl.models.CategoryModel
import edu.com.softserveinc.bawl.services.CategoryService
import edu.com.softserveinc.bawl.services.impl.CategoryServiceImpl
import org.junit.{Before, Test}
import org.junit.runner.RunWith
import org.mockito.{InjectMocks, Mock, MockitoAnnotations}
import org.mockito.Mockito._
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.web.server.MockMvc
import org.springframework.test.web.server.request.MockMvcRequestBuilders.{get,post}
import org.springframework.test.web.server.result.MockMvcResultMatchers.{content, status}
import org.springframework.test.web.server.setup.MockMvcBuilders

@RunWith(classOf[SpringJUnit4ClassRunner])
@ContextConfiguration(locations = Array("classpath:test-root-context.xml", "classpath:test-data-context.xml", "classpath:test-mail-context.xml", "classpath:test-root-context.xml"))
class CategoryControllerTest {

  private var mockMvc: MockMvc = null
  @InjectMocks private var categoryController: CategoryController = null
  @Mock private var categoryService: CategoryService = null


  @Before
  def setup {
    MockitoAnnotations.initMocks(this)
    mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build
  }

  @Test
  @throws(classOf[Exception])
  def testGetCategories {
    val categories = new util.ArrayList[CategoryModel]
    val model: CategoryModel = new CategoryModel
    model.setId(1)
    model.setName("foobar")
    categories.add(model)
    when(categoryService.loadCategoriesList()).thenReturn(categories)
    mockMvc.perform(get("/categories")).andExpect(status.isOk).andExpect(content.string("[{\"id\":1,\"name\":\"foobar\"}]"))
  }

  @Test
  @throws(classOf[Exception])
  def testAddNewCategory {
    val model: CategoryModel = new CategoryModel
    model.setId(1)
    model.setName("foobar")
    mockMvc.perform(post("/categories/add").contentType(MediaType.APPLICATION_JSON).body("{\"id\":1,\"name\":\"foobar\"}".getBytes)).andExpect(status.isOk)

  }

}