package edu.com.softserveinc.bawl.dto.pojo

import scala.beans.BeanProperty

class ResponseDTO {
  @BeanProperty  var message: String = null
  @BeanProperty  var label: String = null
  @BeanProperty  var value: String = null
}