/**
 * Created by xjSaber on 2016/12/10.
 */

//Use a conditional expression.kt

//fun max(a: Int, b: Int) = if (a > b) a else b
//
//fun main(args: Array<String>) {
//    println(max(Integer.parseInt("1"), Integer.parseInt("2")))
//}


//Null-checks.kt

//fun parseInt(str: String): Int? {
//    try{
//        return Integer.parseInt(str)
//    } catch (e: NumberFormatException) {
//        println("One of the arguments isn't Int")
//    }
//    return null;
//}
//
//fun main(args: Array<String>) {
//    if (args.size < 2){
//        println("No number supplied");
//    } else {
//        val x = parseInt(args[0])
//        val y = parseInt(args[0])
//
//        // We cannot say 'x * y' now because they may hold nulls
//        if (x != null && y != null) {
//            print(x * y)
//        }
//        else {
//            println("One of the arguments is null")
//        }
//    }
//}

fun main(args: Array<String>) {
//    val x = Integer.parseInt(args[0]);
    val x = Integer.parseInt("11");
    //Check if a number lies within a range:
    val y = 10
    if (x in 1..y - 1)
        println("OK")

    //Iterate over a range:
    for (a in 1..5)
        print("$a")

    //Check if a number is out of range:
    println()
    val array = arrayListOf<String>()
    array.add("aaa")
    array.add("bbb")
    array.add("ccc")

    if (x !in 0..array.size - 1)
        println("Out: array has only ${array.size} elements. x = ${x}")

    //Check if a collection contains an object:
    if ("aaa" in array) // collection.contains(obj) is called
        println("Yes: array contains aaa")

    if ("ddd" in array) // collection.contains(obj) is called
        println("Yes: array contains ddd")
    else
        println("No: array doesn't contains ddd")
}


