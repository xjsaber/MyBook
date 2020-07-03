package ch02

object Exercise {
  def main(args: Array[String]): Unit = {
    // 创建一个list
    var list0 = List(2, 5, 9, 7, 8, 10)
    // 将list中的偶数取出生成一个新的集合
    var list1 = list0.map(_*2)
    // 将list排序后生成一个新的集合
    var list2 = list0.sortBy(_)
    list2.apply(println(_))
    // 反转顺序排序

    // 将Iterator转换成list

    // 将多个list压扁成一个list
  }
}
