package com.showmeyourcode.playground.java.overview;

import com.showmeyourcode.playground.kotlin.common.Descriptions;
import lombok.extern.slf4j.Slf4j;

import java.util.function.UnaryOperator;


@Slf4j
public class Functions {

    private Functions(){}

    public static void main(String[] args){
        log.info("{} Functions", Descriptions.INDENT1);
        log.info("A function is a block of organized, reusable code that is used to perform a single, related action.");
        UnaryOperator<Void> f = unused -> null;
        f.apply(null);


        log.info(
        """
        
        Function vs Method

        Function — a set of instructions that perform a task.
        Method — a set of instructions that are associated with an object.
        """
        );

        log.info("\n==> Anonymous functions");
        // https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
        log.info("""
                Java doesn't have "anonymous functions" and I don't see that term being used
                except by perhaps people used to languages where they do exist or trying to draw parallels with them.
                In the Java world, they are typically called lambda's.
                A lambda is an instance of a class which has a single function (method).
                Often these classes have a functional interface.
                """
        );

        log.info("==> Lambda functions");
        // https://www.geeksforgeeks.org/lambda-expressions-java-8/
        // https://blog.knoldus.com/java-lambda-vs-anonymous-class/
        log.info("""
                In Java, Lambda expressions basically express instances of functional interfaces
                (An interface with a single abstract method is called a functional interface).
                
                A lambda expression is a short form for writing an anonymous class.
                By using a lambda expression, we can declare methods without any name.
                Whereas, Anonymous class is an inner class without a name,
                which means that we can declare and instantiate class at the same time.
                """);
    }
}
