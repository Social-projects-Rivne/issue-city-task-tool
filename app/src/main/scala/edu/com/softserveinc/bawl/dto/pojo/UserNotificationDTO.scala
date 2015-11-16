package edu.com.softserveinc.bawl.dto.pojo

import java.io.Serializable

import scala.beans.BeanProperty

class UserNotificationDTO extends ResponseDTO with Serializable {
  @BeanProperty  var email: String = null
  @BeanProperty  var subject: String = null
}