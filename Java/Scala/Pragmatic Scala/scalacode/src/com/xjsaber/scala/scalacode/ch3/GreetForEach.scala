package com.xjsaber.scala.scalacode.ch3

object GreetForEach {
  def main(args: Array[String]): Unit = {
    (1 to 3).foreach(i => print(s"$i,"))
    println("Scala Rocks!!!")
  }
}
