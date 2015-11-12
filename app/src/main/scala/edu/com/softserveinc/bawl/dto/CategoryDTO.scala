package edu.com.softserveinc.bawl.dto

import java.util.List

import scala.beans.BeanProperty

class CategoryDTO {
  @BeanProperty var id: Int = 0
  @BeanProperty var name: String = null
  @BeanProperty var state: String = null
  @BeanProperty var issueDtoList: List[IssueDTO] = null
  @BeanProperty var message: String = null
}