# 协程
一个进程可以产生许多线程，每个线程有自己的上下文，当我们在使用多线程的时候，如果存在长时间的 I/O 操作，线程会一直处于阻塞状态，这个时候会存在很多线程处于空闲状态，会造成线程资源的浪费。这就是协程适用的场景。

协程的方式更多用来做阻塞密集型（比如 I/O）的操作，计算密集型的还是使用线程更加合理。

# 常用实现
* [quasar](https://github.com/puniverse/quasar)
* [ea-async](https://github.com/electronicarts/ea-async)
* [OpenJDK Loom](https://github.com/openjdk/loom)
* Cactoos
* Jcabi-Aspects
* vertx-sync

# 参考
[Java 中的协程库 - Quasar](https://www.cnblogs.com/jmcui/p/12511623.html)  
[How long does it take to make a context switch?](https://blog.tsunanet.net/2010/11/how-long-does-it-take-to-make-context.html)