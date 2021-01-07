我们有时候可能需要以下功能
* 可以在加载class文件之前做拦截把字节码做修改
* 可以在运行期将已加载的类的字节码做修改
* 获取所有已加载过的类
* 获取所有已经被初始化过的类
* 获取某个对象的大小
* 将某个jar加入到bootstrapclasspath里作为高优先级被bootstrapClassloader加载
* 将某个jar加入到classpath里作为供AppClassloader加载
* 设置某个native方法的前缀，主要在查找native方法的时候做规则匹配

恭喜你，java提供了java agent技术，满足以上需求  
# java agent 是什么？
java agent是 java 命令的一个参数  
启动时加载javaAgent是jdk1.5之后引入的新特性，此特性为用户提供了在jvm将字节码文件读入内存之后，jvm使用对应的字节流在java堆中生成一个class对象之前，用户可以对其字节码进行修改的能力，从而jvm也将会使用用户修改过之后的字节码进行class对象的创建

# JVM Tool Interface  
JVMT是jvm暴露出来的一些供用户进行自定义扩展的接口结合，JVMT是基于事件驱动的，当jvm执行到一些特定的逻辑时，就会触发回调这些接口，用户就恰好可以在回调这些接口之中做一些自定义的逻辑

JVMTIAgent是一个利用JVMTI暴露出来的接口提供了代理启动时加载（agent on load), 代理通过attach形式加载(agent on attach) 和 代理卸载（agent on unload）功能的动态库

JPLISAgent就是用作实现javaAgent功能的动态库

# JPLISAgent（Java programming language instrumentation services agent）

instrument agent 可以理解为一类 JVMTIAgent 动态库，别名JPLISAgent, 也就是专门为Java语言编写的插桩服务提供支持的代理

JPLISAgent实现了Agent_Onload方法，Agent_Onload方法也就是整个启动时加载的JavaAgent的入口方法

#参考
[JavaAgent原理与实践](https://www.infoq.cn/article/fH69pYPqZPF6Cj1UJy7X)  