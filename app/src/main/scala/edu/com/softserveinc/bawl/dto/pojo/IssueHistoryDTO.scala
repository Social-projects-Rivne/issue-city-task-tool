package edu.com.softserveinc.bawl.dto.pojo

import java.util.Date

import scala.beans.BeanProperty

class IssueHistoryDTO extends ResponseDTO {
  @BeanProperty  var date: Date = null
  @BeanProperty  var changedByUser: String = null
  @BeanProperty  var status: String = ""
}