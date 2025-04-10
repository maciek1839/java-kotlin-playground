package com.showmeyourcode.playground.java.release;

import com.showmeyourcode.playground.kotlin.common.Descriptions;
import lombok.extern.slf4j.Slf4j;

import java.text.NumberFormat;
import java.util.Locale;

@Slf4j
public class Jdk17 {

    private Jdk17() {
    }

     static void main() {
        log.info("\n{} JDK 17", Descriptions.INDENT1);
        log.info("JDK 17 reached General Availability on 14th September 2021.");
        log.info("https://www.oracle.com/java/technologies/javase/17-relnote-issues.html");
        sealedClasses();
        patternMatching();
        records();
        textBlocks();
        compactNumberFormatting();
    }

    private static void compactNumberFormatting() {
        // https://symphony-solutions.com/insights/java-12-17-features
        log.info("\n{} Compact Number Formatting Support", Descriptions.INDENT2);
        log.info("""
                NumberFormat is the abstract base class for all number formats.
                This class provides the interface for formatting and parsing numbers.
                """
        );


        NumberFormat shortFormat = NumberFormat.getCompactNumberInstance(Locale.US,
                NumberFormat.Style.SHORT);
        shortFormat.setMaximumFractionDigits(2);

        NumberFormat longFormat = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.LONG);

        log.info("Format short: {} Format long: {}",
                shortFormat.format(123435.546786543),
                longFormat.format(123435.546786543)
        );
    }

    private static void patternMatching() {
        log.info("\n{} Pattern Matching for instanceof", Descriptions.INDENT2);
        log.info(
                """
                        Pattern matching allows common logic in a program,
                        namely the conditional extraction of components from objects,
                        to be expressed more concisely and safely.
                                                
                        Pattern matching involves testing whether an object has a particular structure,
                        then extracting data from that object if there's a match.
                        """
        );

        Animal kangaroo = new Kangaroo("Polo");

        if (kangaroo instanceof Kangaroo k) {
            k.jump();
        }
    }

    private static void records() {
        log.info("\n{} Records", Descriptions.INDENT2);
        log.info("""
                Records are immutable data classes that require only the type and name of fields.
                The equals, hashCode, and toString methods, as well as the public, final fields and constructor,
                are generated by the Java compiler.
                """);
        // https://stackoverflow.com/questions/76843519/how-to-hide-constructor-on-a-java-record-that-offers-a-public-static-factory-met
        log.info("""
                Records just 'get all that stuff', for free, but at a cost:
                They are inherently defined by their 'parts'.
                This has all sorts of effects - they cannot extend anything (Because that would mean they are defined
                by a combination of their parts and the parts their supertype declares,
                that's more complex than is intended), and the parts are treated as final,
                and you can't make it act like somehow it isn't actually just a thing that groups together its parts,
                which is the problem with your code.
                """
        );

        record MyRecord(String name) {
            public MyRecord() {
                this("default-no-argument-constructor");
            }
        }

        var record = new MyRecord();
        log.info("Record: {} {}", record.name, record.name());
    }

    private static void sealedClasses() {
        log.info("\n{} Sealed classes", Descriptions.INDENT2);
        log.info(
                "{} Sealed classes and interfaces restrict which other classes or interfaces may extend or implement them.",
                Descriptions.INDENT3
        );
        log.info("By using a sealed class, we can have a switch-case that doesn't need a default branch.");

        Animal animal = new Husky("Marco");
        animal.print();
    }

    private static void textBlocks() {
        log.info("\n{} Text Blocks", Descriptions.INDENT2);
        log.info(
                "Java text blocks simplify multiline Strings by using a group of three quotes at the beginning and end."
        );
        log.info(
                """
                         - - - -
                        | 0   0 |
                        |   0   |
                        | 0   0 |
                         - - - -
                        """
        );
    }
}

@Slf4j
sealed class Animal permits Cat, Dog, Kangaroo {
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    void print() {
        log.info("An animal: {}", name);
    }
}

non-sealed class Dog extends Animal {
    private String breed;

    public Dog(String name, String breed) {
        super(name);
        this.breed = breed;
    }
}

class Husky extends Dog {

    public Husky(String name) {
        super(name, "Husky");
    }
}

final class Cat extends Animal {
    private String color;

    public Cat(String name, String color) {
        super(name);
        this.color = color;
    }
}

@Slf4j
sealed class Kangaroo extends Animal {

    public Kangaroo(String name) {
        super(name);
    }

    void jump() {
        log.info("Hoop hoop");
    }
}

final class AustralianKangaroo extends Kangaroo {

    public AustralianKangaroo(String name) {
        super(name);
    }
}
