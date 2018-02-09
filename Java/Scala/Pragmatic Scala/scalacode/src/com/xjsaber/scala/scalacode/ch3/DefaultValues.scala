package com.xjsaber.scala.scalacode.ch3

object DefaultValues {
  def mail(destination: String = "head office", mailClass: String = "first"): Unit = println(s"sending to $destination by $mailClass class")

  def main(args: Array[String]): Unit = {
    print(mail())
  }
}
