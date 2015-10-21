package edu.com.softserveinc.bawl.controllers

import edu.com.softserveinc.bawl.services.CategoryService
import org.junit.{Before, Test}
import org.junit.runner.RunWith
import org.mockito.{Mockito, InjectMocks, Mock, MockitoAnnotations}
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.web.server.MockMvc
import org.springframework.test.web.server.request.MockMvcRequestBuilders.{delete, get, post, put}
import org.springframework.test.web.server.result.MockMvcResultMatchers.{status, view}
import org.springframework.test.web.server.setup.MockMvcBuilders

@RunWith(classOf[SpringJUnit4ClassRunner])
@ContextConfiguration(locations = Array("classpath:test-root-context.xml", "classpath:test-data-context.xml", "classpath:test-mail-context.xml", "classpath:test-root-context.xml")) class CategoryControllerTest {

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
    mockMvc.perform(get("/get-categories")).andExpect(status.isOk)
  }

}