package com.showmeyourcode.playground.java.overview.exception;

import com.showmeyourcode.playground.kotlin.common.Descriptions;

import static com.showmeyourcode.playground.java.LanguageOverview.LOGGER;

public class Exceptions {

    private Exceptions(){}

    public static void main() {
        LOGGER.info(Descriptions.header(Descriptions.EXCEPTION_TITLE));
        LOGGER.info(Descriptions.EXCEPTION);
        // https://stackoverflow.com/questions/498217/when-should-throwable-be-used-instead-of-new-exception
        LOGGER.info("An Error is something extremely rare, that is almost impossible to recover from.");

        LOGGER.info("""
                
                Always throw an Exception (never a Throwable).
                You generally don't catch Throwable either, but you can.
                
                Throwable is the superclass to Exception and Error,
                so you would catch Throwable if you wanted to not only catch Exceptions but Errors,
                that's the point in having it. 
                
                The thing is, Errors are generally things which a normal application wouldn't and shouldn't catch, 
                so just use Exception unless you have a specific reason to use Throwable.
                """
        );

        LOGGER.info("""
                Common Java Exceptions:
                - JVM Exceptions
                    These are exceptions/errors that are exclusively or logically thrown by the JVM.
                    Examples: NullPointerException, ArrayIndexOutOfBoundsException, ClassCastException.
                - Programmatic Exceptions
                    These exceptions are thrown explicitly by the application or the API programmers. 
                    Examples: IllegalArgumentException, IllegalStateException.
                """
        );
        // Ref: https://www.tutorialspoint.com/java/java_exceptions.htm

        // checked exceptions
        // throw new Exception();
        // throw new IOException();
        // throw new Throwable();
        // unchecked exceptions
        // throw new RuntimeException();
        // throw new Error();
    }
}
