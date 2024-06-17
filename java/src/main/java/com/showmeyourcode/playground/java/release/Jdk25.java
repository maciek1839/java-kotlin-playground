package com.showmeyourcode.playground.java.release;

import com.showmeyourcode.playground.kotlin.common.Descriptions;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Jdk25 {

    private Jdk25() {
    }

    public static void main() {
        log.info("{} JDK 25", Descriptions.INDENT1);

//        stringTemplates();
    }
//
//    private static void stringTemplates(String string1, String string2, String string3) {
//        // https://www.baeldung.com/java-21-string-templates
//        LOGGER.info("\n{} String templates", Descriptions.INDENT2);
//        LOGGER.info("""
//                Programmable template expressions can perform interpolation
//                but also provide us with the flexibility to compose the Strings safely and efficiently.
//                """.stripIndent()
//        );
//
//
//        STR. """
//                {
//                    "string1": \{string1},
//                    "string2": \{string2},
//                    "string3": \{string3}
//                    "expression": \{}
//                }
//                """;
//
//    }
}
