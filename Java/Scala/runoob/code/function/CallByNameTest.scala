/**
  * Created by xjsaber on 2017/4/27.
  */
object CallByNameTest {

  def main(args: Array[String]) {
    delayed(time())
  }

  def time(): Long = {
    println("获取时间，单位为纳秒")
    System.nanoTime
  }
  def delayed( t: => Long ): Long = {
    println("在 delayed 方法内")
    println("参数： " + t)
    t
  }
}
