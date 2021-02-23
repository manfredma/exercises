# 编译时代理（静态代理）
# 运行时代理（动态代理）
# JDK 代理
# Spring 代理


**spring 支持静态代理和动态代理，其中动态代理是指运行时代理，主要实现方式有两种，一种是JDK代理，一种是基于cglib的代理。**

spring 代理类的生成可以参阅 DefaultAopProxyFactory 类的实现。