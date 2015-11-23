package edu.com.softserveinc.bawl.dto.pojo

import scala.beans.BeanProperty


class CommentDTO extends ResponseDTO[CommentDTO] {
  @BeanProperty  var id : Int = 0
  @BeanProperty  var issueId : Int = 0
  @BeanProperty  var comment: String = null
  @BeanProperty  var userName : String = null
  @BeanProperty  var email : String = null

}
