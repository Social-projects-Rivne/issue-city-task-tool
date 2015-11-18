package edu.com.softserveinc.bawl.controllers

import java.util

import com.fasterxml.jackson.databind.ObjectMapper
import edu.com.softserveinc.bawl.models.{IssueModel, CategoryModel}
import edu.com.softserveinc.bawl.services.{IssueService, CategoryService}
import org.junit.{Before, Test}
import org.mockito.Mockito._
import org.mockito.{InjectMocks, Mock, MockitoAnnotations}
import org.springframework.http.MediaType
import org.springframework.test.web.server.MockMvc
import org.springframework.test.web.server.request.MockMvcRequestBuilders.{get, post, delete, put}
import org.springframework.test.web.server.result.MockMvcResultMatchers.{content, status}
import org.springframework.test.web.server.setup.MockMvcBuilders

class CategoryControllerFunctionalTest extends CategoryControllerTestData {

  private var mockMvc: MockMvc = null
  @InjectMocks private var categoryController: CategoryController = null
  @Mock private var categoryService: CategoryService = null
  @Mock private var issueService: IssueService = null


  @Before
  def setup() {
    MockitoAnnotations.initMocks(this)
    mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build
  }

  @Test
  @throws(classOf[Exception])
  def testGetCategories() {
    val categories = new util.ArrayList[CategoryModel]
    val model: CategoryModel = new CategoryModel
    model.setId(1)
    model.setName(FOOBAR)
    categories.add(model)
    when(categoryService.loadCategoriesList).thenReturn(categories)
    val objectMapper : ObjectMapper = new ObjectMapper
    mockMvc.perform(get(GET)).andExpect(status.isOk).andExpect(content.string(OBJECT_IN_COLLECTION))
  }

  @Test
  @throws(classOf[Exception])
  def testAddNewCategory() {
    val model: CategoryModel = new CategoryModel
    model.setId(ID)
    model.setName(FOOBAR)
    mockMvc.perform(post(POST).contentType(MediaType.APPLICATION_JSON).body(OBJECT.getBytes)).andExpect(status.isOk)
  }

  @Test
  @throws(classOf[Exception])
  def testEditCategory() {
    val model: CategoryModel = new CategoryModel
    model.setId(ID)
    mockMvc.perform(put(PUT, ID ).contentType(MediaType.APPLICATION_JSON).body(OBJECT.getBytes)).andExpect(status.isOk)
  }

  @Test
  @throws(classOf[Exception])
  def testRemoveCategory() {
    val model: CategoryModel = new CategoryModel
    model.setId(ID)
    mockMvc.perform(delete(DELETE, ID ).contentType(MediaType.APPLICATION_JSON).body(OBJECT.getBytes)).andExpect(status.isOk)
  }



}