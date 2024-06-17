package com.showmeyourcode.playground.java.code.pattern.creational;


import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

// One of the ways we can implement this pattern in Java is by using the clone() method.
//
// The Java Object class does not implement the Cloneable interface. It does however have the clone() method.
// But this method is protected and will throw CloneNotSupportedException if called on an object that does not implement
// the Cloneable interface. So if you cannot modify the class you want to clone you're out of luck and will have
// to find another way to copy the instance.
// https://stackoverflow.com/questions/8192223/object-cloning-with-out-implementing-cloneable-interface
@Slf4j
@SuppressWarnings("java:S2975")
public class Prototype {

    @Data
    private static class Address implements Cloneable {
        private String street;
        private String city;

        public Address(String street, String city) {
            this.street = street;
            this.city = city;
        }

        /**
         * Accordingly to SonarLint:
         * The Object.clone / java.lang.Cloneable mechanism in Java should be considered broken for the following reasons and should, consequently, not be used:
         * - Cloneable is a marker interface without API but with a contract about class behavior that the compiler cannot enforce. This is a bad practice.
         * - Classes are instantiated without calling their constructor, so possible preconditions cannot be enforced.
         * - There are implementation flaws by design when overriding Object.clone, like type casts or the handling of CloneNotSupportedException exceptions.
         *
         * @return a copy of the object
         */
        @Override
        protected Address clone() throws CloneNotSupportedException {
            return (Address) super.clone();
        }
    }

    @ToString
    private static class Person implements Cloneable {
        private final String name;
        private final int age;
        private Address address;

        public Person(String name, int age, Address address) {
            this.name = name;
            this.age = age;
            this.address = address;
        }

        public Person clone() throws CloneNotSupportedException {
            // use a default cloning mechanism to perform shallow copy
            Person cloned = (Person) super.clone();
            // Deep clone the Address object
            cloned.address = this.address.clone();
            return cloned;
        }
    }

    public static void main(String[] args) {
        Address address = new Address("123 Main St", "Anytown");
        Person person = new Person("John Doe", 21, address);

        try {
            Person person2 = person.clone();
            log.info("Person: {} Person2: {} Address: {}", person, person2, address);
        } catch (CloneNotSupportedException e) {
            log.error("Cannot clone! ", e);
        }
    }
}
