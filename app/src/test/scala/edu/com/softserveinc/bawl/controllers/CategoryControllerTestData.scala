package edu.com.softserveinc.bawl.controllers

import org.junit.runner.RunWith
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(classOf[SpringJUnit4ClassRunner])
@ContextConfiguration(locations = Array("classpath:test-root-context.xml"))
abstract class CategoryControllerTestData {

  val FOOBAR : String = "foobar"
  val OBJECT : String = "{\"id\":1,\"name\":\"" + FOOBAR +"\",\"state\":\"New\",\"issueDtoList\":null,\"message\":null}"
  val OBJECT_IN_COLLECTION : String = "[" + OBJECT + "]"
  val EMPTY_COLLECTION : String = "[]"
  val POST : String = "/category"
  val GET : String = "/category/all"


}
