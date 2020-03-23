package com.xjsaber.flink.scala

import org.apache.flink.api.java.ExecutionEnvironment
import org.apache.flink.api.java.operators.DataSource

object Tuple2Job {

  def main(args: Array[String]): Unit = {
    var env = ExecutionEnvironment.getExecutionEnvironment
    //  TypeInformation
    val arrayStream:DataSource[Int] = env.fromCollection(Array(3, 1, 2, 1, 5))
    val listStream:DataSource[Int] = env.fromCollection(List(3, 1, 2, 1, 5))
    arrayStream.print()
    listStream.print()
  }
}
