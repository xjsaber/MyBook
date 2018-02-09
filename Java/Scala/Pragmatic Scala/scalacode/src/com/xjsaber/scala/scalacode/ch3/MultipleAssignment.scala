package com.xjsaber.scala.scalacode.ch3

object MultipleAssignment {
  def getPersonInfo(primaryKey: Int) = {
    ("Venkat", "Subranmaniam", "venkats@agiledeveloper.com")
  }

  val (firstName, lastName, emailAddress) = getPersonInfo(1)

  def main(args: Array[String]): Unit = {
    println(s"First Name: &firstName")
    println(s"Last Name: &firstName")
    println(s"Email Address: &firstName")
  }
}


