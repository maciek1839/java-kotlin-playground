package com.showmeyourcode.playground.java.code.pattern.structural;

// Subject interface
interface Image {
    void display();
}

// Real Subject
class RealImage implements Image {
    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk(fileName);
    }

    private void loadFromDisk(String fileName) {
        System.out.println("Loading " + fileName);
    }

    @Override
    public void display() {
        System.out.println("Displaying " + fileName);
    }
}

// Proxy
class ProxyImage implements Image {
    private RealImage realImage;
    private String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if(realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}

// The Proxy pattern provides a surrogate or placeholder for another object to control access to it.
//
// Differences between Proxy and Decorator Pattern
// 1. Proxy provides the same interface. Decorator provides an enhanced interface.
// 2. Decorator and Proxy have different purposes but similar structures. Both describe how to provide a level of indirection to another object, and the implementations keep a reference to the object to which they forward requests.
// 3. Decorator can be viewed as a degenerate Composite with only one component. However, a Decorator adds additional responsibilities - it isn't intended for object aggregation.
// 4. Decorator supports recursive composition
// 5. The Decorator class declares a composition relationship to the LCD (Lowest Class Denominator) interface, and this data member is initialized in its constructor.
// 6. Use Proxy for lazy initialization, performance improvement by caching the object and controlling access to the client/caller
// https://stackoverflow.com/questions/18618779/differences-between-proxy-and-decorator-pattern
public class Proxy {

    public static void main() {
        Image image = new ProxyImage("test_image.jpg");

        // Image will be loaded from disk
        image.display();
        System.out.println("");

        // Image will not be loaded from disk
        image.display();
    }
}
