package edu.com.softserveinc.bawl.controllers

import edu.com.softserveinc.bawl.models.CategoryModel
import org.junit.{Before, Test}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.server.MockMvc
import org.springframework.test.web.server.request.MockMvcRequestBuilders.{get, post, delete, put}
import org.springframework.test.web.server.result.MockMvcResultMatchers.{content, status}
import org.springframework.test.web.server.setup.MockMvcBuilders

class CategoryControllerIntegrationTest extends CategoryControllerTestData {

  private var mockMvc: MockMvc = null
  @Autowired var categoryController: CategoryController = null


  @Before
  def setup() {
    mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build
  }

  @Test
  @throws(classOf[Exception])
  def testAddNewCategory() {
    val model: CategoryModel = new CategoryModel
    model.setId(ID)
    model.setName(FOOBAR)
    mockMvc.perform(get(GET)).andExpect(status.isOk).andExpect(content.string(EMPTY_COLLECTION))
    mockMvc.perform(post(POST).contentType(MediaType.APPLICATION_JSON).body(OBJECT.getBytes)).andExpect(status.isOk)
    mockMvc.perform(get(GET)).andExpect(status.isOk).andExpect(content.string(OBJECT_IN_COLLECTION))
    mockMvc.perform(delete(DELETE, ID ).contentType(MediaType.APPLICATION_JSON).body(OBJECT.getBytes)).andExpect(status.isOk)
    mockMvc.perform(put(PUT, ID).contentType(MediaType.APPLICATION_JSON).body(OBJECT.getBytes)).andExpect(status.isOk)

  }

}