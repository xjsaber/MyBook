package com.java.scala.code.ch6

class Rational(n: Int, d: Int) {
  override def toString: String = n + "/" + d
}

object code {
  def main(args: Array[String]) {
    val x = new Rational(1, 3)
    val y = new Rational(1, 2)
    println(x)
    println(y)
  }
}
