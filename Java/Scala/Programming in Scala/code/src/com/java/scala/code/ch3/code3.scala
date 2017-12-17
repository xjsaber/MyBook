package com.java.scala.code.ch3

object code3 {
  def main(args: Array[String]) {
    val oneTow = List(1, 2)
    val threeFour = List(1, 2)
    val oneTwoThreeFour = oneTow :: threeFour
    println("" + oneTow + " and " + threeFour + " were not mutated.")
    println("Thus, " + oneTwoThreeFour + " is a new List.")
  }
}
