package com.ilucky.guice;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Scopes;

public class MyModule implements Module {

    public void configure(Binder binder) {
        //binder.bind(MyService.class).to(MyServiceImpl.class);
        binder.bind(MyService.class).to(MyServiceImpl.class).in(Scopes.SINGLETON);
    }
}