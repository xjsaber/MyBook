/**
  * Created by xjsaber on 2017/4/27.
  *
  */
object MatchTest {

  def main(args: Array[String]): Unit = {

    println(matchTest(1))
    println(matchTest(2))
    println(matchTest(3))
  }

  def matchTest(x: Int): String = x match {
    case 1 => "one"
    case 2 => "two"
    case _ => "many"
  }
}
