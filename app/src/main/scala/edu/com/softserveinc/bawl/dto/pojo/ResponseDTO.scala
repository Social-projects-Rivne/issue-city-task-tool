package edu.com.softserveinc.bawl.dto.pojo

import scala.beans.BeanProperty

class ResponseDTO[T] {
  @BeanProperty  var message: String = null
  @BeanProperty  var label: String = null
  @BeanProperty  var value: String = null

  def  withMessage(message : String) : T = {
    this.message = message;
    return this.asInstanceOf[T];
  }
}