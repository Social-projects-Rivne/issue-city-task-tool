package edu.com.softserveinc.bawl.dto.pojo

import java.text.SimpleDateFormat
import java.util.{Comparator, Date}

import scala.beans.BeanProperty

class UserHistoryDTO extends ResponseDTO with Comparable[UserHistoryDTO] with Comparator[UserHistoryDTO] {
   @BeanProperty  var username: String = null
   @BeanProperty  var issueName: String = null
   @BeanProperty  var roleName: String = null
   @BeanProperty  var status: String = ""
   private var date: Date = null
   private var dateStr: String = null

  @Override
  def getDate: String = {
    return dateStr
  }

  def setDate(date : Date) {
    this.date = date
    val simpleDateFormat: SimpleDateFormat = new SimpleDateFormat("dd.mm.yyyy")
    dateStr = simpleDateFormat.format(date)
  }

  def compareTo(o: UserHistoryDTO): Int = {
    return compare(this, o)
  }

  def compare(o1: UserHistoryDTO, o2: UserHistoryDTO): Int = {
    if (o1.date == null || o2.date == null) {
      return -1
    }
    return o1.date.compareTo(o2.date)
  }
}