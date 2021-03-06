package edu.com.softserveinc.bawl.dto.pojo

import scala.beans.BeanProperty

class IssueDTO extends ResponseDTO {
  @BeanProperty var id: Int = 0
  @BeanProperty var name: String = null
  @BeanProperty var description: String = null
  @BeanProperty var mapPointer: String = null
  @BeanProperty var attachments: String = null
  @BeanProperty var categoryId: Int = 0
  @BeanProperty var priorityId: Int = 0
  @BeanProperty var category: String = null
  @BeanProperty var status: String = null
}