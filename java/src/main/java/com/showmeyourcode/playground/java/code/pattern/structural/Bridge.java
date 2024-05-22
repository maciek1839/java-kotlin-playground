package com.showmeyourcode.playground.java.code.pattern.structural;

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
class RedColor implements Color {
    @Override
    public void fill() {
        System.out.println("Filling with red color.");
    }
}

// Concrete Implementor 2
class GreenColor implements Color {
    @Override
    public void fill() {
        System.out.println("Filling with green color.");
    }
}

// Refined Abstraction 1
class Circle extends Shape {
    protected Circle(Color color) {
        super(color);
    }

    @Override
    void applyColor() {
        System.out.print("Circle filled with ");
        color.fill();
    }
}

// Refined Abstraction 2
class Square extends Shape {
    protected Square(Color color) {
        super(color);
    }

    @Override
    void applyColor() {
        System.out.print("Square filled with ");
        color.fill();
    }
}

// The Bridge pattern decouples an abstraction from its implementation so that the two can vary independently.
// https://refactoring.guru/design-patterns/bridge
//
// Difference between Bridge pattern and Adapter pattern?
// https://stackoverflow.com/questions/1425171/difference-between-bridge-pattern-and-adapter-pattern
// The adapter is designed to allow a third party application to work with your application.
// Conversely, so that your application can work with third party applications.
// Using the bridge pattern, it is supposed to connect two or more applications without implementing an adapter.
//
// or
//
// Adapter is used when you have an abstract interface, and you want to map that interface to another object which has similar functional role,
// but a different interface.
//
// Bridge is very similar to Adapter, but we call it Bridge when you define both the abstract interface and the underlying implementation.
// I.e. you're not adapting to some legacy or third-party code,
// you're the designer of all the code but you need to be able to swap out different implementations.
public class Bridge {

    public static void main(){
        Shape redCircle = new Circle(new RedColor());
        Shape greenSquare = new Square(new GreenColor());

        redCircle.applyColor();
        greenSquare.applyColor();
    }
}
