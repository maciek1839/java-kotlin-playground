
package com.showmeyourcode.playground.java.effectivejava._1;
public class SingletonStaticFactory {
    private static SingletonStaticFactory INSTANCE = new SingletonStaticFactory();

    public void doSomething(){ }

    public static SingletonStaticFactory getInstance(){
        return INSTANCE;
    }
}
