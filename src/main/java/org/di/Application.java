package org.di;

import org.di.injector.Injector;

public class Application {
    public static void main(String[] args) {
        Injector injector = Injector.getInstance();
        injector.printContext();
    }
}