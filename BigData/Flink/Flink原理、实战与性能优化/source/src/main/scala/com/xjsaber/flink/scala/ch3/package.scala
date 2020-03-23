package com.xjsaber.flink.scala

import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}

package object WordCount {
  def main(args: Array[String]): Unit = {
    // 第一步设定环境设定
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    // 第二步指定数据源地址，读取输入数据
    val text = env.readTextFile("file://")
    val counts:DataStream[(String, Int)] = text.flatMap(_.toLowerCase.split(" "))
      .filter(_.nonEmpty)
      .map((_, 1))
      .keyBy(0)
      .sum(1)

    env.execute("Streaming WordCount")

  }
}
