package edu.com.softserveinc.bawl.dto.pojo

import java.util.Comparator

import scala.beans.BeanProperty


class CommentDTO extends ResponseDTO[CommentDTO]  with Comparable[CommentDTO]  {
  @BeanProperty  var id : Int = 0
  @BeanProperty  var issueId : Int = 0
  @BeanProperty  var comment: String = null
  @BeanProperty  var userName : String = null
  @BeanProperty  var email : String = null
  @BeanProperty  var avatar : String = null

//  def compareTo(o: UserHistoryDTO): Int = {
//    return compare(this, o)
//  }
//
//  def compare(o1: UserHistoryDTO, o2: UserHistoryDTO): Int = {
//    if (o1.date == null || o2.date == null) {
//      return -1
//    }
//    return o1.date.compareTo(o2.date)
//  }
  def compareTo(o: CommentDTO): Int = {
  return this.id.compareTo(o.getId);
}
}
