package com.showmeyourcode.playground.java.overview.polymorphism;

import lombok.extern.slf4j.Slf4j;

// https://stackoverflow.com/questions/19998454/when-to-use-java-8-interface-default-method-vs-abstract-method
// https://www.infoworld.com/article/2077421/abstract-classes-vs-interfaces-in-java.html
// https://www.digitalocean.com/community/tutorials/difference-between-abstract-class-and-interface-in-java
@Slf4j
public abstract class MyAbstract {

    String example(){
        return "ExampleMethod";
    }

    public static void main(String[] args) {
        MyAbstract abstractInstance = new MyAbstract() {
        };
        MyInterface myInterface = () -> {
        };

        log.info("MyAbstract: {} | {} MyInterface: {}",
                abstractInstance,
                abstractInstance.example(),
                myInterface
        );
    }
}
