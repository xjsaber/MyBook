package com.xjsaber.scala.scalacode.ch5

object Function {

  def commentOnPractice(input: String): Option[String] =
  {
    // 而不是返回null
    if (input == "test") Some("good") else None
  }

  def main(args: Array[String]): Unit = {
    for (input <- Set("test", "hack")) {
      val comment = commentOnPractice(input)
      val commentDisplay = comment.getOrElse("found no comments")
      println(s"input: $input comment: $commentDisplay")
    }
  }
}
