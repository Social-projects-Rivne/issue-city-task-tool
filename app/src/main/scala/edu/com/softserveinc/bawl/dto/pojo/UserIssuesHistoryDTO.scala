package edu.com.softserveinc.bawl.dto.pojo

import scala.beans.BeanProperty

class UserIssuesHistoryDTO extends ResponseDTO {
  @BeanProperty  var issueName: String = null
  @BeanProperty  var issueHistoryDto: IssueHistoryDTO = null
  @BeanProperty  var currentStatus: String = ""
}