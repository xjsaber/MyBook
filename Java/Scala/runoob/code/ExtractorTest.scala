/**
  * Created by xjsaber on 2017/4/27.
  */
object ExtractorTest {

//  def main(args: Array[String]): Unit = {
//    println("Apply 方法 : " + apply("Zara", "gmail.com"))
//    println("Unapply 方法 : " + unapply("Zara@gmail.com"))
//    println("Unapply 方法 : " + unapply("Zara Ali"))
//  }
//
//  // 注入方法（可选）
//  def apply(user: String, domain: String): String = {
//    user + "@" + domain
//  }
//
//  def  unapply(str: String) : Option[(String, String)] = {
//    val parts = str split "@"
//    if (parts.length == 2) {
//      Some(parts(0), parts(1))
//    } else {
//      None
//    }
//  }

  def main(args: Array[String]): Unit = {

    val x = ExtractorTest(5)
    println(x)

    x match {
      case ExtractorTest(num) => println(x + "是" + num + " 的两倍！")
      //unapply被调用
      case _ => println("无法计算")
    }
  }

  def apply(x: Int): Int = x * 2
  def unapply(z: Int): Option[Int] = if (z % 2 == 0) Some(z/2) else None
}
