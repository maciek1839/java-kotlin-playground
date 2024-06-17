package com.showmeyourcode.playground.java.overview.exception;

import com.showmeyourcode.playground.kotlin.common.Descriptions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Exceptions {

    private Exceptions(){}

    public static void main(String[] args) {
        log.info(Descriptions.header(Descriptions.EXCEPTION_TITLE));
        log.info(Descriptions.EXCEPTION);
        // https://stackoverflow.com/questions/498217/when-should-throwable-be-used-instead-of-new-exception
        log.info("An Error is something extremely rare, that is almost impossible to recover from.");

        log.info("""
                
                Always throw an Exception (never a Throwable).
                You generally don't catch Throwable either, but you can.
                
                Throwable is the superclass to Exception and Error (they are subclasses),
                so you would catch Throwable if you wanted to not only catch Exceptions but Errors,
                that's the point in having it.
                
                The thing is, Errors are generally things which a normal application wouldn't and shouldn't catch,
                so just use Exception unless you have a specific reason to use Throwable.
                
                The class Exception and any subclasses that are not also subclasses of RuntimeException are checked exception.
                """
        );

        log.info("""
                Common Java Exceptions:
                - JVM Exceptions
                    These are exceptions/errors that are exclusively or logically thrown by the JVM.
                    Examples: NullPointerException, ArrayIndexOutOfBoundsException, ClassCastException.
                - Programmatic Exceptions
                    These exceptions are thrown explicitly by the application or the API programmers.
                    Examples: IllegalArgumentException, IllegalStateException.
                """
        );

        log.info("""
                Checked exceptions:
                - IOException
                - FileNotFoundException
                Unchecked exceptions:
                - NullPointerException
                - ArrayIndexOutOfBoundsException
                - ArithmeticException
                """
        );
    }
}
