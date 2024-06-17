package com.showmeyourcode.playground.java.code.paradigm.oop;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

// Vehicle class demonstrating encapsulation and abstraction
@Getter
@Slf4j
class Vehicle {
    // Encapsulation: The Vehicle class has private fields (brand, speed, fuelCapacity, mileage)
    // and public getter and setter methods to access and modify these fields.
    // This ensures that the internal state of the object is protected from unauthorized access.
    @Setter
    private String brand;
    private int speed;
    @Setter
    private int fuelCapacity; // in liters
    @Setter
    private int mileage; // in kilometers per liter

    // Constructor
    public Vehicle(String brand, int speed, int fuelCapacity, int mileage) {
        this.brand = brand;
        this.speed = speed;
        this.fuelCapacity = fuelCapacity;
        this.mileage = mileage;
    }

    public void setSpeed(int speed) {
        if (speed >= 0) {
            this.speed = speed;
        }
    }

    // Abstraction: The Vehicle class uses a private method calculateFuelEfficiency
    // to encapsulate the logic for calculating fuel efficiency.
    // The public method getFuelEfficiency calls this private method,
    // providing a simplified interface for users of the class without exposing the internal details.
    //
    // Public method to get fuel efficiency
    public int getFuelEfficiency() {
        return calculateFuelEfficiency();
    }

    // Private method to calculate fuel efficiency (abstraction)
    private int calculateFuelEfficiency() {
        return mileage * fuelCapacity; // This is a simplification
    }

    public void displayInfo() {
        log.info("Brand: {} Speed: {} km/h, Fuel Efficiency: {} km" , brand, speed, getFuelEfficiency());
    }

    // Method to demonstrate polymorphism
    public void makeSound() {
        log.info("Vehicle makes a sound");
    }
}

// Car class demonstrating inheritance and polymorphism
@Getter
@Slf4j
class Car extends Vehicle {
    @Setter
    private int numDoors;

    public Car(String brand, int speed, int fuelCapacity, int mileage, int numDoors) {
        super(brand, speed, fuelCapacity, mileage);
        this.numDoors = numDoors;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        log.info("Number of doors: {}", getNumDoors());
    }

    @Override
    public void makeSound() {
        super.makeSound();
        log.info("Car honks");
    }
}

/**
 * Encapsulation is the practice of bundling the data (fields) and the methods (functions) that operate on that data into a single unit, called a class. It restricts direct access to some of an object's components, which can prevent the accidental modification of data. This is typically achieved by making fields private and providing public getter and setter methods.
 * --
 * Inheritance allows a class to inherit fields and methods from another class. The class that is inherited from is called the superclass, and the class that inherits is called the subclass. This feature promotes code reusability and creates a parent-child relationship between classes.
 * --
 * Polymorphism allows objects of different classes to be treated as objects of a common superclass. It enables one interface to be used for a general class of actions. There are two types of polymorphism: static (method overloading) and dynamic (method overriding).
 * Static polymorphism, also known as method overloading, occurs when multiple methods with the same name exist in the same class but have different parameter lists (different type, number, or both).
 * Dynamic polymorphism, also known as method overriding, occurs when a subclass provides a specific implementation of a method that is already defined in its superclass. The method to be called is determined at runtime based on the object's actual type.
 * --
 * Abstraction is the concept of hiding the complex implementation details and showing only the essential features of the object.
 * It allows focusing on what an object does instead of how it does it.
 * Abstraction can be achieved with either abstract classes or interfaces.
 */
public class ObjectOrientedProgramming {

    private ObjectOrientedProgramming() {
    }

    public static void main(String[] args){
        Car myCar = new Car("Toyota", 120, 50, 15, 4);
        myCar.setSpeed(150);
        myCar.displayInfo();
        myCar.makeSound();
    }
}
