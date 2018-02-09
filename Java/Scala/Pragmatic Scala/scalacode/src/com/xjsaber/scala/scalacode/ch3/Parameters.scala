package com.xjsaber.scala.scalacode.ch3

object Parameters{
  def max(values: Int*) = values.foldLeft(values(0)) {Math.max}

  def main(args: Array[String]): Unit = {
    println(max(8, 2, 3))
  }
}


