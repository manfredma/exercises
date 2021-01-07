# 协程
**协程适用场景**  
一个进程可以产生许多线程，每个线程有自己的上下文，当我们在使用多线程的时候，如果存在长时间的 I/O 操作，线程会一直处于阻塞状态，这个时候会存在很多线程处于空闲状态，会造成线程资源的浪费。 

协程的方式更多用来做阻塞密集型（比如 I/O）的操作，计算密集型的还是使用线程更加合理。

# 常用实现
* [quasar](https://github.com/puniverse/quasar)
* [ea-async](https://github.com/electronicarts/ea-async)
* [OpenJDK Loom](https://github.com/openjdk/loom)
* [vertx-sync](https://github.com/vert-x3/vertx-sync/)  
  基于 quasar 解决 vert.x 异步化编程的难点
* [Cactoos](https://github.com/yegor256/cactoos)  
  项目未成熟（2021-01-04）
* [Jcabi-Aspects](https://github.com/jcabi/jcabi-aspects)  
  Jcabi-Aspects 通过AspectJ AOP方面为异步编程提供Async注释。
  

# 参考

[异步编程的几种方式](https://ericfu.me/several-ways-to-aync/)  
+ *文章内容不错，介绍了 CPS 变换等*
[繁杂网络IO型业务的分析及探索--协程和响应式](https://juejin.cn/post/6844904178993594381)
---
**上下文切换**  
[How long does it take to make a context switch?](https://blog.tsunanet.net/2010/11/how-long-does-it-take-to-make-context.html)  
[进程/线程上下文切换会用掉你多少CPU？](https://zhuanlan.zhihu.com/p/79772089)
--- 
**协程**  
[次时代Java编程(一) Java里的协程](https://segmentfault.com/a/1190000005342905)  
[干货——Java异步编程](https://www.jianshu.com/p/e752ed187826)  
[硬核系列 | 深入剖析 Java 协程](https://xie.infoq.cn/article/cef6d2931a54f85142d863db7)
[协程，高并发IO的终级杀器(1)](https://zhuanlan.zhihu.com/p/27519705)  
[爱奇艺网络协程编写高并发应用实践](https://mp.weixin.qq.com/s?__biz=MzAwMDU1MTE1OQ==&mid=2653553219&idx=1&sn=e9e7e473bc71684a0239140eacd12081&chksm=813993dbb64e1acd73e749c72fc7697cfcf04209a22eb888a32c37a8552b6df5da22d891d1ec&scene=21#wechat_redirect)  
[白话高并发中的协程](https://mp.weixin.qq.com/s/xCP0lxc9Gc3QiBUmrsDWyg)  
---
**quasar**  
[携程基于Quasar协程的NIO实践](https://www.infoq.cn/article/umyqimhkwiyjhbp05mxb)  
[Java 中的协程库 - Quasar](https://www.cnblogs.com/jmcui/p/12511623.html)  
[继续了解Java的纤程库 - Quasar](https://colobu.com/2016/08/01/talk-about-quasar-again/)  
[java 协程 quasar 从原理到代码应用](https://blog.csdn.net/guzhangyu12345/article/details/84666423)  
[关于并发问题的梳理及Quasar/Vert.X等组件学习整理](https://zhuanlan.zhihu.com/p/57826725)
[Java中的纤程库 - Quasar](https://colobu.com/2016/07/14/Java-Fiber-Quasar/)
[继续了解Java的纤程库 - Quasar](https://colobu.com/2016/08/01/talk-about-quasar-again/)
---
**vertx-sync**  
[次时代Java编程(一)：续 vertx-sync实践](https://segmentfault.com/a/1190000006098059)
--- 
**Loom**  
[如何看待Project Loom?](https://www.zhihu.com/question/67579790)

