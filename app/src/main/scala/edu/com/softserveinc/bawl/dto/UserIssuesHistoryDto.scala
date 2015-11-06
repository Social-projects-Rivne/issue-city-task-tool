package edu.com.softserveinc.bawl.dto

import scala.beans.BeanProperty

class UserIssuesHistoryDTO {
  @BeanProperty  var issueName: String = null
  @BeanProperty  var issueHistoryDto: IssueHistoryDTO = null
  @BeanProperty  var currentStatus: String = ""
}