package com.showmeyourcode.playground.java.code.pattern.structural;

abstract class Party1 {
    private String id;
    private String name;
    private String contactDetails;

    public Party1(String id, String name, String contactDetails) {
        this.id = id;
        this.name = name;
        this.contactDetails = contactDetails;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContactDetails() {
        return contactDetails;
    }
}

class Person extends Party1 {
    private String firstName;
    private String lastName;

    public Person(String id, String firstName, String lastName, String contactDetails) {
        super(id, firstName + " " + lastName, contactDetails);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}

class Organization extends Party1 {
    private String organizationName;

    public Organization(String id, String organizationName, String contactDetails) {
        super(id, organizationName, contactDetails);
        this.organizationName = organizationName;
    }

    public String getOrganizationName() {
        return organizationName;
    }
}

class PartyRole {
    private Party1 party;
    private String roleType;

    public PartyRole(Party1 party, String roleType) {
        this.party = party;
        this.roleType = roleType;
    }

    public Party1 getParty() {
        return party;
    }

    public String getRoleType() {
        return roleType;
    }
}

class PartyRelationship {
    private Party1 party1;
    private Party1 party2;
    private String relationshipType;

    public PartyRelationship(Party1 party1, Party1 party2, String relationshipType) {
        this.party1 = party1;
        this.party2 = party2;
        this.relationshipType = relationshipType;
    }

    public Party1 getParty1() {
        return party1;
    }

    public Party1 getParty2() {
        return party2;
    }

    public String getRelationshipType() {
        return relationshipType;
    }
}

// This structure allows you to manage different types of participants, their roles, and their relationships in a flexible and extensible manner, following the Party Pattern.
public class Party {

    public static void main() {
        Person person = new Person("1", "John", "Doe", "john.doe@example.com");
        Organization organization = new Organization("2", "Example Corp", "contact@example.com");

        PartyRole customerRole = new PartyRole(person, "Customer");
        PartyRole employeeRole = new PartyRole(person, "Employee");
        PartyRole supplierRole = new PartyRole(organization, "Supplier");

        PartyRelationship employment = new PartyRelationship(person, organization, "Employment");

        System.out.println("Person: " + person.getName());
        System.out.println("Organization: " + organization.getName());
        System.out.println("Role: " + customerRole.getRoleType());
        System.out.println("Relationship: " + employment.getRelationshipType() + " between " +
                employment.getParty1().getName() + " and " + employment.getParty2().getName());
    }
}
