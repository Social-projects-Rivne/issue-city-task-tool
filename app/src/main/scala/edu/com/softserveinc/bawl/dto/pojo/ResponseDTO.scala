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

  def  withLabel(label : String) : T = {
    this.label = label;
    return this.asInstanceOf[T];
  }

  def  withValue(value : String) : T = {
    this.value = value;
    return this.asInstanceOf[T];
  }

  def  withValue(value : Int) : T = {
    this.value = value.toString;
    return this.asInstanceOf[T];
  }

  def  withValue(value : Long) : T = {
    this.value = value.toString;
    return this.asInstanceOf[T];
  }
}