package edu.com.softserveinc.bawl.dto

import scala.beans.BeanProperty


class CommentDTO {
  @BeanProperty  var id : Int = 0
  @BeanProperty  var issueId : Int = 0
  @BeanProperty  var comment: String = null
  @BeanProperty  var userName : String = null
  @BeanProperty  var email : String = null
}
