import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

// При асинхронном вызове функций время работы меньше, поскольку при последовательном вызове 
// функции выполняются одна за другой, а при асинхронном вызове - параллельно.

suspend fun returnNumber1(): Int {
    delay(1000L)
    return 42
}

suspend fun returnNumber2(): Int {
    delay(1500L)
    return 24
}

suspend fun sequentialSum() {
    println("Sequential invocation")
    val seq1 = returnNumber1()
    val seq2 = returnNumber2()
    println("Sum: ${seq1 + seq2}")
}

suspend fun asyncSum() = coroutineScope {
    println("Async invocation")
    val async1 = async { returnNumber1() }
    val async2 = async { returnNumber2() }
    println("Sum: ${async1.await() + async2.await()}")
}

fun main() = runBlocking {
    val sequentialTime = measureTimeMillis {
        sequentialSum()
    }
    println("Time: $sequentialTime ms")
    val asyncTime = measureTimeMillis {
        asyncSum()
    }
    println("Time: $asyncTime ms")
}
