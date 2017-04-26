/**
  * Created by xjsaber on 2017/4/26.
  */
object ListTest {

  // 字符串列表
  val site: List[String] = List("Runoob", "Google", "Baidu")

  // 整型列表
  val nums: List[Int] = List(1, 2, 3, 4)

  // 空列表
  val empty: List[Nothing] = List()

  // 二维列表
  val dim: List[List[Int]] =
    List(
      List(1, 0, 0),
      List(0, 1, 0),
      List(0, 0, 1)
    )

  def ListConcat() {
    val site1 = "Runoob" :: ("Google" :: ("Baidu" :: Nil))
    val site2 = "Facebook" :: ("Taobao" :: Nil)

    // 使用 ::: 运算符
    var fruit = site1 ::: site2
    println( "site1 ::: site2 : " + fruit )

    // 使用 Set.:::() 方法
    fruit = site1.:::(site2)
    println( "site1.:::(site2) : " + fruit )

    // 使用 concat 方法
    fruit = List.concat(site1, site2)
    println( "List.concat(site1, site2) : " + fruit  )
  }

  def listFill() {
    val site = List.fill(3)("Runoob") // 重复 Runoob 3次
    println( "site : " + site  )

    val num = List.fill(10)(2)         // 重复元素 2, 10 次
    println( "num : " + num  )
  }

  def main(args : Array[String]): Unit = {


  }
}
