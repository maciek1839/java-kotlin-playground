package com.showmeyourcode.playground.java.overview;

import com.showmeyourcode.playground.kotlin.common.Descriptions;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;

@Slf4j
public class Classes {

    private Classes(){
        parentMethod();
    }

    private void parentMethod(){
        // logic here
    }

    // Java inner class is defined inside the body of another class.
    class InnerClass {

        void method1(){
            // the inner class can access parent's data
            Classes.this.parentMethod();
        }
    }

    static class StaticInnerClass{

        void method(){
            // logic here
        }
    }

    public static void main(String[] args) {
        log.info("\n{} Classes", Descriptions.INDENT1);
        // https://en.wikipedia.org/wiki/Class_(computer_programming)
        log.info("""
                A class is an extensible program-code-template for creating objects,
                providing initial values for state (member variables)
                and implementations of behavior (member functions or methods).
                """
        );

        // https://stackoverflow.com/questions/11398122/what-are-the-purposes-of-inner-classes
        // https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html
        // https://www.javatpoint.com/java-inner-class
        // https://www.digitalocean.com/community/tutorials/java-inner-class
        log.info("""
                Java inner class or nested class is a class that is declared inside the class or interface.
                
                Conceptually inner classes can be used to represent types in the universe
                that would not exist without that parent type.
                                
                Example:
                Class Bob is used to collect, store, and process a very specific set of data that only has one purpose.
                Class Bob.ErrorType was then built to identify the unique error (enumerator),
                the degree of “fatal-ness”, how the error was fixed or ignored,
                and a friendly error message that put it all together.
                There were three main advantages of nesting ErrorType within Bob:
                1. Bob.ErrorType’s individual members (including the important numerator) were not exposed outside of Class Bob.
                2. Bob.ErrorType was able to access other private members of Class Bob that also should not be exposed to the rest of the project.
                3. Bob.ErrorType was a special-purpose class that could and should only be used by Class Bob and no other classes in the project.
                """
        );
        InnerClass innerClass = new Classes().new InnerClass();
        innerClass.method1();
        StaticInnerClass staticInnerClass = new Classes.StaticInnerClass();
        staticInnerClass.method();

        log.info("Inner class: {} Static inner class: {}", innerClass, staticInnerClass);

        // https://www.baeldung.com/java-anonymous-classes
        // https://stackoverflow.com/questions/355167/how-are-anonymous-inner-classes-used-in-java
        // https://www.digitalocean.com/community/tutorials/java-inner-class
        log.info("""
                A local inner class without name is known as anonymous inner class.
                An anonymous class is defined and instantiated in a single statement.
                Anonymous inner class always extend a class or implement an interface.
                
                Local Inner Classes are the inner classes that are defined inside a block.
                Generally, this block is a method body.
                Sometimes this block can be a for loop, or an if clause.
                Local Inner classes are not a member of any enclosing classes.
                They belong to the block they are defined within,
                due to which local inner classes cannot have any access modifiers associated with them.
                """
        );
        // A class i.e., created inside a method, is called local inner class in java.
        // Local Inner Classes are the inner classes that are defined inside a block.
        class LocalInner {
            private final long data = Instant.now().getEpochSecond();
            void msg() {
                log.info("Local inner class. Data: {}", data);
            }
        }
        LocalInner l = new LocalInner();
        l.msg();

        log.info("""
                
                Anonymous classes are inner classes with no name.
                In other words, anonymous inner classes don't have a name, they do have a type.
                Since they have no name, we can’t use them in order to create instances of anonymous classes.
                As a result, we have to declare and instantiate anonymous classes in a single expression at the point of use.
                """
        );

        var anonymousClass = new Runnable() {
            @Override
            public void run() {
                // logic here
            }
        };
        anonymousClass.run();
    }
}
