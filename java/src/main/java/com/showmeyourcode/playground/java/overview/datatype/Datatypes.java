package com.showmeyourcode.playground.java.overview.datatype;

import com.showmeyourcode.playground.kotlin.common.Descriptions;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Datatypes {

    private Datatypes(){}

    public static void main(String[] args) {
        log.info("{}", Descriptions.header(Descriptions.DATA_TYPE_TITLE));
        log.info(Descriptions.DATA_TYPE);

        objectType();
        signedUnsignedVariables();
        typeInference();
        rawType();
    }

    private static void rawType() {
        log.info("\n{} Raw Type", Descriptions.INDENT1);
        log.info("If the actual type argument is omitted, you create a raw type of Box<T>: Box rawBox = new Box();");
        log.info("A list is a raw type, while List<String> is a parameterized type.");
        log.info("""
                
                Note that the reason why raw types exist is for backwards compatibility with Java 1.4 and older,
                which did not have generics at all.
                """
        );
        // https://docs.oracle.com/javase/tutorial/java/generics/rawTypes.html
        // https://stackoverflow.com/questions/2770321/what-is-a-raw-type-and-why-shouldnt-we-use-it
        log.info("""
                Using raw types is still possible, they should be avoided:           
                - They usually require casts.
                - They aren't type safe, and some important kinds of errors will only appear at runtime.
                - They are less expressive, and don't self-document in the same way as parameterized types..
                """
        );
    }

    private static void typeInference() {
        log.info("\n{} Type Inference", Descriptions.INDENT1);
        log.info("Java does have type inferencing when using generics.");
        log.info("""
                With var , the Java compiler infers the type of the variable at compile time,
                using type information obtained from the variable's initializer.
                """
        );
    }

    private static void objectType() {
        log.info("Primitive data types are predefined. Object data types are user-defined.");
        log.info("Primitives simply represent a value, like the number seven or the boolean value of false.");
        log.info("They have no methods and no complementary properties.");
        log.info("Also, you can't do much with them other than inspect their value or perhaps change the value that they hold.");
        log.info("But they are not, by any means, objects.\n");
        log.info("Object == 1.2L:       {}", false);
        log.info("Object == \"String\":   {}", "String" instanceof Object);
        log.info("Object == 'a':        {}", false);
        log.info("Object == 'int[]':    {}", new int[]{1, 2, 3} instanceof Object);
        log.info("Object == 'Integer':  {}", Integer.valueOf(1) instanceof Object);
        log.info("Object == 'Integer':  {}", Integer.valueOf(1) instanceof Object);
    }

    private static void signedUnsignedVariables() {
        // https://stackoverflow.com/questions/21089540/signed-and-unsigned-data-types-in-java
        log.info("\n{} Signed/Unsigned variables", Descriptions.INDENT1);
        log.info("Signed variables, such as signed integers will allow you to represent numbers both in the positive and negative ranges.");
        log.info("Unsigned variables, such as unsigned integers, will only allow you to represent numbers in the positive.\n");

        log.info("Java only supports signed types (except char), in other words all Java numeric types are signed. This was the designers decision.");
        log.info("'0' * 1.1: {}", '0' * 1.1);

        // https://stackoverflow.com/questions/430346/why-doesnt-java-support-unsigned-ints/15053268#15053268
        log.info("""
            Update! With JDK8 there are new APIs for Long and Integer which provide helper methods
            when treating long and int values as unsigned values.
            """
        );

        int w = 150;
        int z = 10;

        log.info("Integer.divideUnsigned(150, 10): {}", Integer.divideUnsigned(w, z));

        int m = -150;
        int n = 10;

        log.info("Integer.divideUnsigned(-150, 10): {}", Integer.divideUnsigned(m, n));
    }
}
