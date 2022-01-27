package manfred.end

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    GlobalScope.launch(Dispatchers.Default) {
        delay(1000);
        print("Kotlin coroutines（" + Thread.currentThread() + ")");
    }

    print("hello, （" + Thread.currentThread() + ")");

    Thread.sleep(2000);

    print(" world（" + Thread.currentThread() + ")");
}


