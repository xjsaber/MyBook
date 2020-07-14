package com.xjsaber.learn.scala.code

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._

class main {
  private val conf = new SparkConf().setMaster("local").setAppName("My Quick")
  val sc = new SparkContext(conf)
}
