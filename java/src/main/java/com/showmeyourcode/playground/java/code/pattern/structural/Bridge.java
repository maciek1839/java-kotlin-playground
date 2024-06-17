package com.showmeyourcode.playground.java.code.pattern.structural;

import lombok.extern.slf4j.Slf4j;

/**
 * The Bridge pattern decouples an abstraction from its implementation so that the two can vary independently.
 * <a href="https://refactoring.guru/design-patterns/bridge">Design patterns - bridge</a>
 * <br>
 * Difference between Bridge pattern and Adapter pattern?
 * <a href="https://stackoverflow.com/questions/1425171/difference-between-bridge-pattern-and-adapter-pattern">See this thread</a>
 * <br>
 * The adapter is designed to allow a third party application to work with your application.
 * Conversely, so that your application can work with third party applications.
 * Using the bridge pattern, it is supposed to connect two or more applications without implementing an adapter.
 * <br>
 * or
 * <br>
 * Adapter is used when you have an abstract interface, and you want to map that interface to another object which has similar functional role,
 * but a different interface.
 * <br>
 * Bridge is very similar to Adapter, but we call it Bridge when you define both the abstract interface and the underlying implementation.
 * I.e. you're not adapting to some legacy or third-party code,
 * you're the designer of all the code but you need to be able to swap out different implementations.
 */
public class Bridge {

    private Bridge() {
    }

    public static void main(String[] args){
        Shape redCircle = new Circle(new RedColor());
        Shape greenSquare = new Square(new GreenColor());

        redCircle.applyColor();
        greenSquare.applyColor();
    }
}


// Abstraction
abstract class Shape {
    protected Color color;

    protected Shape(Color color) {
        this.color = color;
    }

    abstract void applyColor();
}

// Implementor interface
interface Color {
    void fill();
}

// Concrete Implementor 1
@Slf4j
class RedColor implements Color {
    @Override
    public void fill() {
        log.info("Filling with red color.");
    }
}

// Concrete Implementor 2
@Slf4j
class GreenColor implements Color {
    @Override
    public void fill() {
        log.info("Filling with green color.");
    }
}

// Refined Abstraction 1
@Slf4j
class Circle extends Shape {
    protected Circle(Color color) {
        super(color);
    }

    @Override
    void applyColor() {
        log.info("Circle filled with ");
        color.fill();
    }
}

// Refined Abstraction 2
@Slf4j
class Square extends Shape {
    protected Square(Color color) {
        super(color);
    }

    @Override
    void applyColor() {
        log.info("Square filled with ");
        color.fill();
    }
}
