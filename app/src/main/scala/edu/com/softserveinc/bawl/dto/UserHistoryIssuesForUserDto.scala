package edu.com.softserveinc.bawl.dto

import scala.beans.BeanProperty

class UserHistoryIssuesForUserDto {
  @BeanProperty var nameIssue: String = null
  @BeanProperty var history: List[IssueHistoryDTO] = null

}
