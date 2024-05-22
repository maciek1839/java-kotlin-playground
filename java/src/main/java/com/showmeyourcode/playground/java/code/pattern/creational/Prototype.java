package com.showmeyourcode.playground.java.code.pattern.creational;


// One of the ways we can implement this pattern in Java is by using the clone() method.
//
// The Java Object class does not implement the Cloneable interface. It does however have the clone() method.
// But this method is protected and will throw CloneNotSupportedException if called on an object that does not implement
// the Cloneable interface. So if you cannot modify the class you want to clone you're out of luck and will have
// to find another way to copy the instance.
// https://stackoverflow.com/questions/8192223/object-cloning-with-out-implementing-cloneable-interface
public class Prototype {

    private static class Address implements Cloneable {
        private String street;
        private String city;

        public Address(String street, String city) {
            this.street = street;
            this.city = city;
        }

        @Override
        protected Address clone() throws CloneNotSupportedException {
            return (Address) super.clone();
        }

        @Override
        public String toString() {
            return "Address{" +
                    "street='" + street + '\'' +
                    ", city='" + city + '\'' +
                    '}';
        }

        // Getters and setters
        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }
    }

    private static class Person implements Cloneable {
        private String name;
        private int age;
        private Address address;

        public Person(String name, int age, Address address) {
            this.name = name;
            this.age = age;
            this.address = address;
        }

        public Person clone() throws CloneNotSupportedException {
            Person cloned = (Person) super.clone();
            // Deep clone the Address object
            cloned.address = this.address.clone();
            return cloned;
        }
    }

    public static void main() {
        Address address = new Address("123 Main St", "Anytown");
        Person prototype = new Person("John Doe", 21, address);

        try {
            prototype.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
