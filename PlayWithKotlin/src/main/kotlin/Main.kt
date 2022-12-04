fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    println(sum1(1,2))
    println(sum2(2,2))

    println("Got a sum of ${strSum(-1,1)}")

    val number = 1234
    val textVal = "Whatever"
    val setMeLater : Int


    var reassignMe = 123;

    setMeLater = 34

    reassignMe = setMeLater+1

    class HoldingFewProps(var a : Int, var b: String) {
        var c = "$b and $a";
    }

    val haveFew = HoldingFewProps(10,"S")
    println(haveFew)
    println(haveFew.a)
    println(haveFew.b)
    println(haveFew.c)

    val valAsExpression = if (reassignMe == 35) "I WIN" else "I'm ELSE"
    println(valAsExpression)

}

fun strSum(a: Int, b: Int) = "${a} and $b"


fun sum1(a :Int, b:Int) = a+b

fun sum2(a: Int, b:Int) :Int {
    return a+b
}

