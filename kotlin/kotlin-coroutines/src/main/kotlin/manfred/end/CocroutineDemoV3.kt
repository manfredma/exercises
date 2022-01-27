package manfred.end

import kotlinx.coroutines.*
import java.util.concurrent.atomic.AtomicLong
import kotlin.concurrent.thread

fun main() {
    val deferred = (1..1_000_000).map { n ->
        GlobalScope.async {
            delay(1000)
            n
        }
    }
    runBlocking {
        val sum = deferred.sumOf { it.await().toLong() }
        println("Sum: $sum")
    }

}


