# 简介

> classloader顾名思义，即是类加载。虚拟机把描述类的数据从class字节码文件加载到内存，并对数据进行检验、转换解析和初始化，最终形成可以被虚拟机直接使用的Java类型，这就是虚拟机的类加载机制。

classLoader 的作用是将 class 的字节码加载到虚拟机中，其过程一般为：  
加载 &rarr; (验证 &rarr; 准备 &rarr; 解析)  &rarr;初始化
> 为了方便记忆，我们可以使用一句话来表达其加载的整个过程，“家宴准备了西式菜”，即家(加载)宴(验证)准备(准备)了西(解析)式(初始化)菜。

JVM 提供给开发者可以干预的环节主要是 **加载** 环节

![加载过程](img/加载过程.png)

# 双亲委派模型

![双亲委派模型](img/双亲委派机制.png)

**原则**   
类 A 引用到类 B，则由类 A 的加载器去加载类 B，保证引用到的类被一同载入到系统， 即：类 B 只能是由类 A 的加载器加载或者类 A 加载器的父加载器加载到系统中来。

**解决问题**

* 重复类的加载问题
* 核心类的安全问题

**classloader 种类**

1. 启动类加载器(Bootstrap Classloader)  
   负责将<JAVA_HOME>/lib目录下并且被虚拟机识别的类库加载到虚拟机内存中。  
   我们常用基础库，例如java.util.**，java.io.**，java.lang.**等等都是由根加载器加载。
2. 扩展类加载器(Extension Classloader)
   负责加载JVM扩展类，比如swing系列、内置的js引擎、xml解析器等，这些类库以javax开头，它们的jar包位于<JAVA_HOME>/lib/ext目录中。
3. 应用程序加载器(Application Classloader)  
   也叫*系统类加载器*，它负责加载用户路径(ClassPath)上所指定的类库。  
   我们自己编写的代码以及使用的第三方的jar包都是由它来加载的。
4. 自定义加载器(Custom Classloader)  
   通常是我们为了某些特殊目的实现的自定义加载器，如果没有明确其父加载器，则使用 Application Classloader 作为其父加载器。

**代码实现**  
![classloader_loadClass](img/classloader_loadClass.png)

## 双亲委派模型反例

### 线程上下文类加载器 Context ClassLoader

```
public class Thread implements Runnable {  
     /* The context ClassLoader for this thread */  
     private ClassLoader contextClassLoader;  
     
     public void setContextClassLoader(ClassLoader cl) {  
          SecurityManager sm = System.getSecurityManager();  
          if (sm != null) {  
               sm.checkPermission(new RuntimePermission("setContextClassLoader"));  
          }  
          contextClassLoader = cl;  
     }  
     public ClassLoader getContextClassLoader() {  
          if (contextClassLoader == null)  
               return null;  
          SecurityManager sm = System.getSecurityManager();  
          if (sm != null) {  
                ClassLoader.checkClassLoaderPermission(contextClassLoader,  
                    Reflection.getCallerClass());  
          }  
          return contextClassLoader;  
     }  
}
```

**线程上下文类加载器 存在的原因？**  
设计的初衷主要是为了解决上面的原则，如果类 A 引用了类 B，则类 B 默认会使用类 A 的加载器进行加载。但是对于一些 SPI 机制，需要第三方去实现 Java 核心包中的接口，而核心包中类使用 bootstrap classloader
进行加载，该加载器必然无法加载到第三方提供的扩展实现。  
当然，可以写死使用 Application classloader 进行加载，但是这样就会导致比较脆弱，无法满足自定义类加载器的实现。
![thread_context_loader](img/ThreadContext%20ClassLoader.png)

```
public class DriverManager {
	//省略不必要的代码
	static {
		loadInitialDrivers();//执行该方法
		println("JDBC DriverManager initialized");
	}

	//loadInitialDrivers方法
	private static void loadInitialDrivers() {
		sun.misc.Providers()
			AccessController.doPrivileged(new PrivilegedAction<Void>() {
				public Void run() {
					//加载外部的Driver的实现类
					ServiceLoader<Driver> loadedDrivers = ServiceLoader.load(Driver.class);
					//省略不必要的代码
				}
			});
	}
}
```

### 插件化

为了实现热插拔，热部署，模块化，意思是添加一个功能或减去一个功能不用重启，只需要把这模块连同类加载器一起换掉就实现了代码的热替换。

### tomcat 类加载机制

### springboot 类加载机制

## 实践经验

### 热加载/插件化

### 热部署

### 依赖冲突

### 加密保护

# 参考

[Java类加载器 — classloader 的原理及应用](https://mp.weixin.qq.com/s?__biz=MzAxNDEwNjk5OQ==&mid=2650418560&idx=1&sn=ed3c3ee4184fe48ffdcfb88d6b2aa539&chksm=8396e598b4e16c8ee4d4908d94cc41ed9d183538522b0b14524ec5bdf995e3197b88f70ff0c6&scene=178&cur_album_id=1452661944472977409#rd)  
[一看你就懂，超详细java中的ClassLoader详解](https://blog.csdn.net/briblue/article/details/54973413)  
[深入探索“线程上下文类加载器”](https://www.jianshu.com/p/05ec26e25627)  
[不遵循双亲委派的类加载器 -- 线程上下文类加载器](https://techlog.cn/article/list/10183177)  