package edu.com.softserveinc.bawl.dto.pojo

import java.util.List

import scala.beans.BeanProperty

class CategoryDTO extends ResponseDTO {
  @BeanProperty var id: Int = 0
  @BeanProperty var name: String = null
  @BeanProperty var state: String = null
  @BeanProperty var issueDtoList: List[IssueDTO] = null
}