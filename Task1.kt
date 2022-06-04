import kotlinx.coroutines.*

fun main() = runBlocking {
    launch {
        delay(1000L)
        println("World")
    }
    delay(2000L)
    println("Hello, ")
}
