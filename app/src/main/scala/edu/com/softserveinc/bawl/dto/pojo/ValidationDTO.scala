package edu.com.softserveinc.bawl.dto.pojo

import scala.beans.BeanProperty

class ValidationDTO extends ResponseDTO[UserDTO] {
  @BeanProperty var hash: String = null
  @BeanProperty var id: Int = 0
}