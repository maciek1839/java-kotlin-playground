package com.showmeyourcode.playground.java.code;

import com.showmeyourcode.playground.kotlin.common.Descriptions;

import static com.showmeyourcode.playground.java.LanguageOverview.LOGGER;

public class Classes {

    private Classes(){}

    public static void main() {
        LOGGER.info("\n{} Classes", Descriptions.INDENT1);
        // https://en.wikipedia.org/wiki/Class_(computer_programming)
        LOGGER.info("""
                A class is an extensible program-code-template for creating objects,
                providing initial values for state (member variables)
                and implementations of behavior (member functions or methods).
                """
        );

        // https://stackoverflow.com/questions/11398122/what-are-the-purposes-of-inner-classes
        // https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html
        // https://www.javatpoint.com/java-inner-class
        LOGGER.info("""
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

        // https://www.baeldung.com/java-anonymous-classes
        // https://stackoverflow.com/questions/355167/how-are-anonymous-inner-classes-used-in-java
        // https://www.digitalocean.com/community/tutorials/java-inner-class
        LOGGER.info("""
                                
                Anonymous classes are inner classes with no name.
                In other words, anonymous inner classes don't have a name, they do have a type.
                Since they have no name, we can’t use them in order to create instances of anonymous classes.
                As a result, we have to declare and instantiate anonymous classes in a single expression at the point of use.
                                
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
        var anonymousClass = new Runnable() {
            @Override
            public void run() {
            }
        };
        anonymousClass.run();
    }
}

// https://www.javatpoint.com/local-inner-class
class LocalInner1 {
    private int data = 30;//instance variable

    void display() {
        class Local {
            void msg() {
                LOGGER.info("{}", data);
            }
        }
        Local l = new Local();
        l.msg();
    }

    public static void main(String args[]) {
        LocalInner1 obj = new LocalInner1();
        obj.display();
    }
}
