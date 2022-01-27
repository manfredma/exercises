package manfred.end

import kotlin.concurrent.thread

fun main() {
    thread {
        Thread.sleep(100);
        print("Kotlin coroutines（" + Thread.currentThread() + ")");
    }

    print("hello, （" + Thread.currentThread() + ")");

    Thread.sleep(200);

    print(" world（" + Thread.currentThread() + ")");
}


