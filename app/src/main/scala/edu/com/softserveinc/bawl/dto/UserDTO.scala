package edu.com.softserveinc.bawl.dto

import scala.beans.BeanProperty

class UserDTO {
  @BeanProperty  var id: Int = 0
  @BeanProperty  var name: String = null
  @BeanProperty  var email: String = null
  @BeanProperty  var login: String = null
  @BeanProperty  var roleId: Int = 0
  @BeanProperty  var avatar: String = null
  @BeanProperty  var password : String= null
}