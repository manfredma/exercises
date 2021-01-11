* 什么是字节码？
  > Java之所以可以“一次编译，到处运行”，一是因为JVM针对各种操作系统、平台都进行了定制，二是因为无论在什么平台，都可以编译生成固定格式的字节码（.class文件）供JVM使用。因此，也可以看出字节码对于Java生态的重要性。之所以被称之为字节码，是因为字节码文件由十六进制值组成，而JVM以两个十六进制值为一组，即以字节为单位进行读取。在Java中一般是用javac命令编译源代码为字节码文件，一个.java文件从编译到运行的示例如图1所示。

<div  align="center">    
<img src="https://p0.meituan.net/travelcube/110b593ecf53866e0dec8df3618b0443257977.png" alt="Java运行示意图" style="zoom:50%"/>
</div>

# 参考

[字节码增强技术探索](https://tech.meituan.com/2019/09/05/java-bytecode-enhancement.html)  
[Java动态字节码增强技术原理与应用](https://www.163.com/dy/article/EL1FIVPF05376OPS.html)  
[字节码增强：原理与实战](https://my.oschina.net/vivotech/blog/4680933)  