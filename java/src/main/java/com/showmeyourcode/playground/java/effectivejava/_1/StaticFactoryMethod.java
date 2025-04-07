package com.showmeyourcode.playground.java.effectivejava._1;

public class StaticFactoryMethod {
    private int intProperty;

    public static StaticFactoryMethod valueOf(int intProperty){
        if(intProperty < 0){
            throw new IllegalArgumentException("'intProperty' cannot be negative");
        }

        return new StaticFactoryMethod(intProperty);
    }

    public int doSomething(){
        return intProperty;
    }

    private StaticFactoryMethod(int intProperty){
        this.intProperty = intProperty;
    }
}
