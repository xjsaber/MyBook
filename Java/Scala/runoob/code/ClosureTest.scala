/**
  * Created by xjsaber on 2017/4/26.
  */
object ClosureTest {

  def main(args: Array[String]): Unit = {
    val num = 4
    println(multiplier(num))
  }

  var factor = 3
  var multiplier = (i:Int) => i * factor

}
