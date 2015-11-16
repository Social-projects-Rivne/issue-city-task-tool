package edu.com.softserveinc.bawl.dto.pojo

import scala.beans.BeanProperty

class UserHistoryIssuesForUserDTO {
  @BeanProperty var nameIssue: String = null
  @BeanProperty var history: List[IssueHistoryDTO] = null

}
