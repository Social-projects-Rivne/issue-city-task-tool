package edu.com.softserveinc.bawl.dto.pojo

import scala.beans.BeanProperty

import scala.beans.BeanProperty

class SubscriptionDTO extends ResponseDTO[UserDTO] {
  @BeanProperty  var id : Int = 0
  @BeanProperty  var issueId : Int = 0
  @BeanProperty  var email : String = null
}