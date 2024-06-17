package com.showmeyourcode.playground.java.code.pattern.structural;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * This structure allows you to manage different types of participants,
 * their roles, and their relationships in a flexible and extensible manner, following the Party Pattern.
 */
@Slf4j
public class Party {

    private Party() {
    }

    public static void main(String[] args) {
        Person person = new Person("1", "John", "Doe", "john.doe@example.com");
        Organization organization = new Organization("2", "Example Corp", "contact@example.com");

        PartyRole customerRole = new PartyRole(person, "Customer");
        PartyRole employeeRole = new PartyRole(person, "Employee");
        PartyRole supplierRole = new PartyRole(organization, "Supplier");

        PartyRelationship employment = new PartyRelationship(person, organization, "Employment");

        log.info("Person: {}", person.getName());
        log.info("Organization: {}", organization.getName());
        log.info("Roles: {} {} {}", customerRole.roleType(), employeeRole.roleType(), supplierRole.roleType());
        log.info("Relationship: {} between {} and {}",
                employment.relationshipType(),
                employment.party1().getName(),
                employment.party2().getName()
        );
    }
}

@Getter
abstract class Party1 {
    private final String id;
    private final String name;
    private final String contactDetails;

    Party1(String id, String name, String contactDetails) {
        this.id = id;
        this.name = name;
        this.contactDetails = contactDetails;
    }
}

@Getter
class Person extends Party1 {
    private final String firstName;
    private final String lastName;

    Person(String id, String firstName, String lastName, String contactDetails) {
        super(id, firstName + " " + lastName, contactDetails);
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

@Getter
class Organization extends Party1 {
    private final String organizationName;

    public Organization(String id, String organizationName, String contactDetails) {
        super(id, organizationName, contactDetails);
        this.organizationName = organizationName;
    }
}


record PartyRole(Party1 party, String roleType) {
}


record PartyRelationship(Party1 party1, Party1 party2, String relationshipType) {
}
