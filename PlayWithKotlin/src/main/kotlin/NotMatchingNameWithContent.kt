import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class PartWithCoroutines {
    fun proceed() = runBlocking { // this: CoroutineScope
        val job = launch { // launch a new coroutine and continue
            delay(100L) // non-blocking delay for 1 second (default time unit is ms)
            println("World!") // print after delay
        }
        println("Hello") // main coroutine continues while a previous one is delayed
        job.join();
        println("EOF")

        val timeTook = measureTimeMillis {
            val initial = firstBlocking()
            val snd = sndOne()

            println("Result is ${initial} and ${snd}")
        }

        println("It all took ${timeTook}")

        val timeTookAsync = measureTimeMillis {
            val initial = async(start = CoroutineStart.LAZY) {firstBlocking() }
            val snd = async(CoroutineName("mySampleCoroutineName")) { sndOne()}

            println("Result is ${initial.await()} and ${snd.await()}")
        }
        println("Using async was ${timeTookAsync}")

        val lazyAsyncWorkedAsBlocking = measureTimeMillis {
            val initial = async(start = CoroutineStart.LAZY) {firstBlocking() }
            val snd = async(start = CoroutineStart.LAZY) { sndOne()}

            println("Result is ${initial.await()} and ${snd.await()}")
        }
        println("it works as sync; cause await would start() -> wait for response ${lazyAsyncWorkedAsBlocking}")

        println("And exec using aggegrated context ${execTwoOthers()}");
        followup()
    }

    suspend fun firstBlocking() : String{
        println("First blocking start")
        delay(100)
        println("First blocking end")
        return "First one"
    }

    suspend fun sndOne() : String {
        println("First blocking start")
        delay(200)
        println("First blocking end")
        return "Snd "
    }

    suspend fun execTwoOthers() : String = coroutineScope {
        val first = async { firstBlocking() }
        val snd = async { sndOne() }
        first.await() + snd.await()
    }

    fun followup() = runBlocking {
        launch(Dispatchers.Unconfined) { // not confined -- will work with main thread
            println("Unconfined      : I'm working in thread ${Thread.currentThread().name}")
            delay(500)
            println("Unconfined      : After delay in thread ${Thread.currentThread().name}")
        }
        launch { // context of the parent, main runBlocking coroutine
            println("main runBlocking: I'm working in thread ${Thread.currentThread().name}")
            delay(1000)
            println("main runBlocking: After delay in thread ${Thread.currentThread().name}")
        }
    }
}