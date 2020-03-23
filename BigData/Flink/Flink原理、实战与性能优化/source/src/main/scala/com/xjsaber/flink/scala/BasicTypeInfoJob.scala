package com.xjsaber.flink.scala

import org.apache.flink.api.java.ExecutionEnvironment
import org.apache.flink.api.java.operators.DataSource

object BasicTypeInfoJob {

  def main(args: Array[String]): Unit = {
    var env = ExecutionEnvironment.getExecutionEnvironment
    //  BasicTypeInfo
    val intStream: DataSource[Int] = env.fromElements(3, 1, 2, 1, 5)
    val dataStream: DataSource[String] = env.fromElements("hello", "flink")
    intStream.print()
    dataStream.print()
  }
}
