package com.xjsaber.flink.scala

import com.xjsaber.flink.scala.model.Person
import org.apache.flink.api.java.ExecutionEnvironment
import org.apache.flink.api.java.operators.DataSource
import org.apache.flink.streaming.api.scala.DataStream

object StarterJob {

  def main(args: Array[String]): Unit = {
    var env = ExecutionEnvironment.getExecutionEnvironment

    //  BasicTypeInfo
    val intStream:DataSource[Int] = env.fromElements(3, 1, 2, 1, 5)
    val dataStream:DataSource[String] = env.fromElements("hello", "flink")
    intStream.print()
    dataStream.print()

    //  TypeInformation
//    val dataStream:DataSource[Int] = env.fromCollection(Array<Int>(3, 1, 2, 1, 5));
//    val dataStream:DataSource[Int] = env.fromCollection(List<Int>(3, 1, 2, 1, 5));

    //  通过实例化Tuple2创建具有两个元素的数据集
//    val tupleStream2: DataSource[(String, Int)] = env.fromElements(Tuple2("a", 1), Tuple2("c", 2))

    //  定义WordCount Case Class数据结构
//    case class WordCount(word: String, count: Int)
    //  通过fromElements方法创建数据集
//    val input = env.fromElements(WordCount("hello", 1), WordCount("world", 2))
//    val keyStream1 = input.groupBy("word")
//    val keyStream2 = input.groupBy(0)

//    val tupleStream: DataSource[(String, Int)] = env.fromElements(Tuple2("a", 1), Tuple2("c", 2))
//    tupleStream.groupBy("_1")
//
//    val personStream = env.fromElements(new Person("Peter", 14), new Person("Linda", 25))
    // 通过Person.name来指定KeyBy字段
//    personStream.groupBy("name");

    // 创建Map类型数据集
//    val mapStream = env.fromElements(Map("name"->"Peter", "age"->18), Map("name"->"Linda", "age"->25))
    // 创建List类型数据集
//    val listStream = env.fromElements(List(1, 2, 3, 5), List(2, 4, 3, 2))

  }
}
