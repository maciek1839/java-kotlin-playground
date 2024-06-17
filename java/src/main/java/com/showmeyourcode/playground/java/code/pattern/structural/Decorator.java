package com.showmeyourcode.playground.java.code.pattern.structural;

import lombok.extern.slf4j.Slf4j;

/**
 * The Decorator pattern attaches additional responsibilities to an object dynamically.
 * It provides a flexible alternative to subclassing for extending functionality.
 * <br>
 * Adapter pattern vs Decorator Pattern
 * Decorator, attach additional responsibilities to an object dynamically. For example adding sugar in a coffee.
 * Adapter, adapts interface of an existing class to another interface. For example eletrical adapter.
 * <br>
 * <a href="https://stackoverflow.com/questions/42737096/design-patterns-adapter-pattern-vs-decorator-pattern">Design Patterns - Adapter pattern vs Decorator Pattern?</a>
 */
@Slf4j
public class Decorator {

    private Decorator() {
    }

    public static void main(String[] args){
        ShapeD circle = new CircleD();
        ShapeD redCircle = new RedShapeDecorator(new CircleD());
        ShapeD redRectangle = new RedShapeDecorator(new Rectangle());

        log.info("Circle with normal border");
        circle.draw();

        log.info("\nCircle with red border");
        redCircle.draw();

        log.info("\nRectangle with red border");
        redRectangle.draw();
    }
}

// Component
interface ShapeD {
    void draw();
}

// Concrete Component
@Slf4j
class Rectangle implements ShapeD {
    @Override
    public void draw() {
        log.info("Shape: Rectangle");
    }
}

// Concrete Component
@Slf4j
class CircleD implements ShapeD {
    @Override
    public void draw() {
        log.info("Shape: Circle");
    }
}

// Decorator
abstract class ShapeDecorator implements ShapeD {
    protected ShapeD decoratedShape;

    ShapeDecorator(ShapeD decoratedShape) {
        this.decoratedShape = decoratedShape;
    }

    @Override
    public void draw() {
        decoratedShape.draw();
    }
}

// Concrete Decorator
@Slf4j
class RedShapeDecorator extends ShapeDecorator {
    RedShapeDecorator(ShapeD decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        decoratedShape.draw();
        setRedBorder(decoratedShape);
    }

    private void setRedBorder(ShapeD decoratedShape) {
        log.info("Border Color: Red | Object:{}", decoratedShape);
    }
}
