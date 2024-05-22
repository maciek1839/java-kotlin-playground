package com.showmeyourcode.playground.java.code.pattern.structural;

// Component
interface ShapeD {
    void draw();
}

// Concrete Component
class Rectangle implements ShapeD {
    @Override
    public void draw() {
        System.out.println("Shape: Rectangle");
    }
}

// Concrete Component
class CircleD implements ShapeD {
    @Override
    public void draw() {
        System.out.println("Shape: Circle");
    }
}

// Decorator
abstract class ShapeDecorator implements ShapeD {
    protected ShapeD decoratedShape;

    public ShapeDecorator(ShapeD decoratedShape) {
        this.decoratedShape = decoratedShape;
    }

    @Override
    public void draw() {
        decoratedShape.draw();
    }
}

// Concrete Decorator
class RedShapeDecorator extends ShapeDecorator {
    public RedShapeDecorator(ShapeD decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        decoratedShape.draw();
        setRedBorder(decoratedShape);
    }

    private void setRedBorder(ShapeD decoratedShape) {
        System.out.println("Border Color: Red");
    }
}


// The Decorator pattern attaches additional responsibilities to an object dynamically.
// It provides a flexible alternative to subclassing for extending functionality.
//
// Adapter pattern vs Decorator Pattern
// Decorator, attach additional responsibilities to an object dynamically. For example adding sugar in a coffee.
// Adapter, adapts interface of an existing class to another interface. For example eletrical adapter.
// https://stackoverflow.com/questions/42737096/design-patterns-adapter-pattern-vs-decorator-pattern
public class Decorator {

    public static void main(){
        ShapeD circle = new CircleD();
        ShapeD redCircle = new RedShapeDecorator(new CircleD());
        ShapeD redRectangle = new RedShapeDecorator(new Rectangle());

        System.out.println("Circle with normal border");
        circle.draw();

        System.out.println("\nCircle with red border");
        redCircle.draw();

        System.out.println("\nRectangle with red border");
        redRectangle.draw();
    }
}
