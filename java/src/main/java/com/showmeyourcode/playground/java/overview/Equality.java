package com.showmeyourcode.playground.java.overview;

import com.showmeyourcode.playground.kotlin.common.Descriptions;

import java.util.Objects;

import static com.showmeyourcode.playground.java.Application.LOGGER;

public class Equality {

    private Equality(){}

    public static void main(){
        LOGGER.info(Descriptions.header(Descriptions.EQUALITY));
        // https://www.baeldung.com/java-equals-method-operator-difference
        LOGGER.info("\nequals (Value Equality)");
        LOGGER.info("Value equality takes place when two separate objects happen to have the same values or state.");
        LOGGER.info(
                "\n{} Primitives are basic types with a single value and don’t implement any methods.",
                Descriptions.INDENT1
        );
        LOGGER.info("Therefore, it’s impossible to call the equals() method directly using primitives.");
        LOGGER.info("""
                However, since every primitive has its own wrapper class,
                we can use the boxing mechanism to cast it into its object representation.
                Then, we can easily call the equals() method as if we are using object types
                """
        );

        int a = 10;
        Integer b = a;

        LOGGER.info("Primitives 'equals' using a wrapper class: {}", b.equals(10));

        LOGGER.info("""
                
                {} If we don’t override the equals() method, the method from the parent class Object is used. 
                The Object.equals() method only does reference equality check (==).
                """,
                Descriptions.INDENT1
        );

        Person p1 = new Person("Bob", 20);
        Person p2 = new Person("Mike", 40);
        LOGGER.info("Object 'equals': {}", p1.equals(p2));

        LOGGER.info("\n{} (Reference Equality)", Descriptions.INDENT1);
        LOGGER.info("Reference equality occurs when two references point to the same object (address) in the memory.");
        LOGGER.info("\n{} The primitive types in Java are simple, non-class raw values.", Descriptions.INDENT1);
        LOGGER.info("""
                Java prohibits assigning null to a primitive,
                so we can’t perform any null checks with the equality operator on primitive variables or values.
                """
        );
    }


    public record Person(String name, int age) {

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Person person = (Person) o;
            return age == person.age && Objects.equals(name, person.name);
        }
    }
}
