package com.showmeyourcode.playground.java.code;

import static com.showmeyourcode.playground.java.Application.LOGGER;

public class Functions {

    private Functions(){}

    public static void main(){
        LOGGER.info("====> Functions");
        LOGGER.info("A function is a block of organized, reusable code that is used to perform a single, related action.");

        LOGGER.info("\n==> Anonymous functions");
        // https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
        LOGGER.info("""
                Java doesn't have "anonymous functions" and I don't see that term being used
                except by perhaps people used to languages where they do exist or trying to draw parallels with them.
                In the Java world, they are typically called lambda's.
                A lambda is an instance of a class which has a single function (method).
                Often these classes have a functional interface.
                """
        );

        LOGGER.info("==> Lambda functions");
        // https://www.geeksforgeeks.org/lambda-expressions-java-8/
        // https://blog.knoldus.com/java-lambda-vs-anonymous-class/
        LOGGER.info("""
                In Java, Lambda expressions basically express instances of functional interfaces
                (An interface with a single abstract method is called a functional interface).
                
                A lambda expression is a short form for writing an anonymous class.
                By using a lambda expression, we can declare methods without any name.
                Whereas, Anonymous class is an inner class without a name,
                which means that we can declare and instantiate class at the same time.
                """);
    }
}
