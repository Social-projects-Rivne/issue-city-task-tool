package edu.com.softserveinc.bawl.dto

import java.util.Date

import scala.beans.BeanProperty

class IssueHistoryDTO {
  @BeanProperty  var date: Date = null
  @BeanProperty  var changedByUser: String = null
  @BeanProperty  var status: String = ""
}