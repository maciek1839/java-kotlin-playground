package com.showmeyourcode.playground.java.code.exercise.oop;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * Finish the compareTo() method using all three of the instance variables.
 * Access them using the getX() methods.
 * Compare two employees by first looking at their last names.
 * If the names are different, return the value the String method compareTo() returns with the last names.
 * If the names are equal, look at the first name and return the value the String method compareTo() returns.
 * If both parts of the name are equal,
 * return the difference of the birthYears so that the older employee precedes the younger employee.
 * <br>
 * <a href="https://chortle.ccsu.edu/java5/Notes/chap53B/progExercises53B.html">Exercise source</a>
 */
@Slf4j
public class EmployeeTesterExercise {

    private EmployeeTesterExercise() {
    }

    @Getter
    static class Employee implements Comparable<Employee> {
        private final String firstName;
        private final String lastName;
        private final int birthYear;

        public Employee(String f, String l, int year) {
            firstName = f;
            lastName = l;
            birthYear = year;
        }

        public String toString() {
            return firstName + " " + lastName + " (" + birthYear + ")";
        }

        @Override
        public int compareTo(Employee other) {
            // Compare last names
            int lastNameComparison = this.lastName.compareTo(other.getLastName());
            if (lastNameComparison != 0) {
                return lastNameComparison;
            }

            // Last names are equal, compare first names
            int firstNameComparison = this.firstName.compareTo(other.getFirstName());
            if (firstNameComparison != 0) {
                return firstNameComparison;
            }

            // First names are also equal, compare birth years
            return Integer.compare(this.birthYear, other.getBirthYear());
        }
        // "equals(Object obj)" should be overridden along with the "compareTo(T obj)" method
        //
        // If this rule is violated, weird and unpredictable failures can occur.
        // For example, in Java 5 the PriorityQueue.remove() method relied on compareTo(),
        // but since Java 6 it has relied on equals().
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Employee other = (Employee) obj;
            return birthYear == other.birthYear &&
                    firstName.equals(other.firstName) &&
                    lastName.equals(other.lastName);
        }

        @Override
        public int hashCode() {
            int result = firstName.hashCode();
            result = 31 * result + lastName.hashCode();
            result = 31 * result + birthYear;
            return result;
        }
    }


    @SuppressWarnings("java:S1192")
    public static void main(String[] args) {
        Employee[] workers = new Employee[12];

        workers[0] = new Employee("Fred", "Adams", 1963);
        workers[1] = new Employee("John", "Adams", 1959);
        workers[2] = new Employee("Elmer", "Adams", 1976);
        workers[3] = new Employee("Nancy", "Devon", 1963);
        workers[4] = new Employee("Andrew", "Lewis", 1983);
        workers[5] = new Employee("Douglas", "Page", 1981);
        workers[6] = new Employee("Donald", "Wolder", 1963);
        workers[7] = new Employee("Henry", "Wolder", 1972);
        workers[8] = new Employee("Robert", "Wolder", 1959);
        workers[9] = new Employee("Howard", "Cohen", 1933);
        workers[10] = new Employee("Howard", "Cohen", 1958);
        workers[11] = new Employee("Donald", "Rice", 1935);

        Arrays.sort(workers);

        for (Employee worker : workers) {
            log.info("{}",worker);
        }
    }
}
