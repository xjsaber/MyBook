package com.xjsaber.scala.scalacode.ch3

import java.util

class ScalaInt {
  def playWithInt(): Unit = {
    val capacity: Int = 10
    val list = new util.ArrayList[String]()
    list.ensureCapacity(capacity)
  }
}
