package com.showmeyourcode.playground.java.overview;

import com.showmeyourcode.playground.kotlin.common.Descriptions;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;


@Slf4j
public class Equality {

    private Equality(){}

    public static void main(String[] args){
        log.info(Descriptions.header(Descriptions.EQUALITY));
        // https://www.baeldung.com/java-equals-method-operator-difference
        log.info("\nequals (Value Equality)");
        log.info("Value equality takes place when two separate objects happen to have the same values or state.");
        log.info(
                "\n{} Primitives are basic types with a single value and don’t implement any methods.",
                Descriptions.INDENT1
        );
        log.info("Therefore, it’s impossible to call the equals() method directly using primitives.");
        log.info("""
                However, since every primitive has its own wrapper class,
                we can use the boxing mechanism to cast it into its object representation.
                Then, we can easily call the equals() method as if we are using object types
                """
        );

        int a = 10;
        Integer b = a;

        log.info("Primitives 'equals' using a wrapper class: {}", b.equals(10));

        log.info("""
                
                {} If we don’t override the equals() method, the method from the parent class Object is used.
                The Object.equals() method only does reference equality check (==).
                """,
                Descriptions.INDENT1
        );

        Person p1 = new Person("Bob", 20);
        Person p2 = new Person("Mike", 40);
        log.info("Object 'equals': {}", p1.equals(p2));

        log.info("\n{} (Reference Equality)", Descriptions.INDENT1);
        log.info("Reference equality occurs when two references point to the same object (address) in the memory.");
        log.info("\n{} The primitive types in Java are simple, non-class raw values.", Descriptions.INDENT1);
        log.info("""
                Java prohibits assigning null to a primitive,
                so we can’t perform any null checks with the equality operator on primitive variables or values.
                """
        );
    }


    public record Person(String name, int age) {

        // You should override the equals method in Java when
        // you need to define equality for instances of your class based on your own criteria rather than
        // the default reference equality provided by Object.equals.
        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Person person = (Person) o;
            return age == person.age && Objects.equals(name, person.name);
        }

        // You should override hashCode when you override equals because
        // they are closely linked in the contract of the Object class.
        //
        // According to this contract:
        //- Consistency with equals: If two objects are considered equal according to the equals method,
        //  they must have the same hash code. This ensures that objects can be used correctly in hash-based collections like HashMap, HashSet, and Hashtable.
        //- Efficient Lookup: Hash-based collections use the hash code to locate objects quickly.
        //  If hashCode is not overridden, the default implementation might not distribute objects uniformly,
        //  leading to poor performance.
        //
        // By default, the hashCode() function for an object returns the number of the memory cell where the object is stored.
        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }
    }
}
