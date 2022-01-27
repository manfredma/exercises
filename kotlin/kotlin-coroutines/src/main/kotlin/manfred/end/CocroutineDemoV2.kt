package manfred.end

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicLong

fun main() {
    var begin = System.currentTimeMillis();
    var c = AtomicLong()
    val executor = Executors.newFixedThreadPool(12)


    for (i in 1..10_000L) {
        executor.execute { c.addAndGet(i) };
    }

    println(c.get())

    println("线程耗时：" + (System.currentTimeMillis() - begin));

    begin = System.currentTimeMillis();
    c = AtomicLong()

    for (i in 1..10_000L) {
        GlobalScope.launch {
            c.addAndGet(i)
        }
    }

    println(c.get())
    println("协程耗时：" + (System.currentTimeMillis() - begin));
}


