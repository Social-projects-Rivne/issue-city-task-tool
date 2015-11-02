package edu.com.softserveinc.bawl.dto

import java.io.Serializable

import scala.beans.BeanProperty

class UserNotificationDTO extends Serializable {
  @BeanProperty  var email: String = null
  @BeanProperty  var subject: String = null
  @BeanProperty  var message: String = null
}