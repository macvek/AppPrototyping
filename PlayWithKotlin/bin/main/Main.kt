
fun typeSafeBuilderCheck() {
    class BuildSbj {

        var myVal : String = "not changed"

        fun execute() {
            println("I have val of $myVal")
        }
    }

    fun buildSbj( inContext : BuildSbj.() -> Unit) : BuildSbj {
        val receiver = BuildSbj()
        receiver.inContext()
        return receiver;
    }

    buildSbj {
        myVal = "New one"
        execute()
    }

}


class FunWithClasses(val constructorInt : Int) {
    val propInt : Int
        get() = constructorInt * 2

    val otherProp get() = constructorInt * 3

    var privateGetterPublicSetter: String = "HellYeah"
        private set

    fun changeVal(newVal : String) {
        privateGetterPublicSetter = "Changed to: $newVal"
    }

    lateinit var imLateOne : String

    fun setupImLate(lateVal : String) {
        if (this::imLateOne.isInitialized) {
            println("skipping!!")
        }
        else {
            println("Setting imLateOne")
            imLateOne = lateVal
        }
    }

}

fun funWithObject() {
    val someObj = object {
        val hello = "World"
        var otherVal :Int = 0
    }

    println(someObj.hello)
}

object ImASingleton {
    fun whatever() {
        println("named object is implicit singleton")
    }
}

fun onTheFun() {
    val withClass = FunWithClasses(2)

    val checkPrivAccess: FunWithClasses.() -> Unit = { this.changeVal("I Cannot access private variable") }

    println(withClass.propInt)
    println(withClass.otherProp)
    println(withClass.privateGetterPublicSetter)
    withClass.changeVal("New one")
    println(withClass.privateGetterPublicSetter)

    withClass.setupImLate("Yo")
    withClass.setupImLate("YoYo")
    funWithObject()

    withClass.checkPrivAccess()
    println(withClass.privateGetterPublicSetter)
}

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

    val anyList = listOf("A","B","C");

    for (each in anyList) {
        println("> $each")
    }

    val ranged = 1..5
    if (5 in ranged) {
        println("5 in 1..5")
    }

    println("contains check using 6 in 1..5 ${ranged.contains(6)}")

    typeCheck("Whatever")
    typeCheck(1)

    onTheFun()
    ImASingleton.whatever()

    val lmbd = { a:Int, b:Int -> a*b }
    println(lmbd(2,3))

    val ltrld = fun(a : Int, b:Int) : Int { return a*b }
    println (ltrld(3,4))

    val repeatFun: String.(Int) -> String = { t -> this.repeat(t)}
    println("xO".repeatFun(10))


    fun iGetIntAndLambda(a : Int, b: (Int) -> Int) : Int {
        return b(a);
    }

    println("INLINED IT: "+iGetIntAndLambda(2, { 2*it }))
    println("DEFINED ARG: "+iGetIntAndLambda(2, { t -> 3*t }))
    println("TRAILING LAMBDA: "+iGetIntAndLambda(2) {4 * it})
    println("DON'T CARE VARIABLE using _: "+iGetIntAndLambda(2) {_ -> 1234})

    typeSafeBuilderCheck()
    val following = PartWithCoroutines()
    following.proceed()
}

fun typeCheck(s : Any) {

    val o : Any = s;

    if (o is String) {
        val chr = o.get(0)
        println("I have o[0] = $chr")
    }
    else {
        println("Evaluating o.get(0) would fail, as here o is not a String, check in IDE \${o.get(0)}")
    }

    if (o !is String) {
        println("Similar case for NOT is")
    }

    val iAmNullable = o as? String
    when( iAmNullable) {
        is String? -> println("String? = $iAmNullable")
        else -> println("must be wrong = $iAmNullable")
    }
}

fun strSum(a: Int, b: Int) = "${a} and $b"


fun sum1(a :Int, b:Int) = a+b

fun sum2(a: Int, b:Int) :Int {
    return a+b
}

