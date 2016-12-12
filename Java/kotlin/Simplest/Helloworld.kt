/**
 * Created by xjSaber on 2016/12/10.
 */

fun main(args: Array<String>) {
//    if (args.isEmpty()) {
//        println("Please provide a name as a command-line argument")
//        return
//    }
//    println("Hello, $(args[0])!");

//    val language = if (args.isEmpty()) "EN" else args[0]
//    println(when (language) {
//        "EN" -> "Hello!"
//        "FR" -> "Salut!"
//        "IT" -> "Ciao!"
//        else -> "Sorry, I can't greet you in $language yet"
//    })
    Greeter("阿毛").greet();

}

class Greeter(val name: String){
    fun greet() {
        println("Hello, ${name}");
    }
}
