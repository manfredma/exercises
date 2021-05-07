package com.ilucky.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * v1.0:20161114
 *
 * @author IluckySi
 * 入门实例和单例模式
 * Binder接口作用是创建接口和其实现类的映射关系.
 * Module接口作用是维护一组 Bindings.
 * Injector接口作用是创建对象和维护对象的生命周期.
 * Guice类是创建Injector和Modules的联系.
 */
public class MainTest {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new MyModule());
        MyService myService = injector.getInstance(MyService.class);
        myService.service("Hello Guice!");

        //测试单例模式:binder.bind(MyService.class).to(MyServiceImpl.class).in(Scopes.SINGLETON);.
        MyService myService2 = injector.getInstance(MyService.class);
        System.out.println(myService.equals(myService2));//true.
    }
}
