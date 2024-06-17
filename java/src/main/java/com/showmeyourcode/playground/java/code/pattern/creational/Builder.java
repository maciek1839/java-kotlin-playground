package com.showmeyourcode.playground.java.code.pattern.creational;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Builder {
    // Required parameters
    private final String licenseNumber;
    private final String name;

    // Optional parameters - initialized to default values
    private String address = "";
    private String dateOfBirth = "";
    private String issueDate = "";
    private String expirationDate = "";
    private String issuingAuthority = "";

    public Builder(String licenseNumber, String name) {
        if (licenseNumber == null || licenseNumber.isEmpty()) {
            throw new IllegalArgumentException("License number is required.");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name is required.");
        }
        this.licenseNumber = licenseNumber;
        this.name = name;
    }

    public Builder address(String address) {
        this.address = address;
        return this;
    }

    public Builder dateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public Builder issueDate(String issueDate) {
        this.issueDate = issueDate;
        return this;
    }

    public Builder expirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    public Builder issuingAuthority(String issuingAuthority) {
        this.issuingAuthority = issuingAuthority;
        return this;
    }

    public DriverLicense build() {
        // Additional validation can be added here if needed
        return new DriverLicense(this);
    }

    @Override
    public String toString() {
        return "DriverLicense{" +
                "licenseNumber='" + licenseNumber + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", issueDate='" + issueDate + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", issuingAuthority='" + issuingAuthority + '\'' +
                '}';
    }

    @ToString
    public static class DriverLicense {
        // Required parameters
        private final String licenseNumber;
        private final String name;

        // Optional parameters
        private final String address;
        private final String dateOfBirth;
        private final String issueDate;
        private final String expirationDate;
        private final String issuingAuthority;

        private DriverLicense(Builder builder) {
            this.licenseNumber = builder.licenseNumber;
            this.name = builder.name;
            this.address = builder.address;
            this.dateOfBirth = builder.dateOfBirth;
            this.issueDate = builder.issueDate;
            this.expirationDate = builder.expirationDate;
            this.issuingAuthority = builder.issuingAuthority;
        }
    }

    public static void main(String[] args) {
        try {
            DriverLicense license = new Builder("123456789", "John Doe")
                    .address("123 Main St")
                    .dateOfBirth("01/01/1980")
                    .issueDate("01/01/2020")
                    .expirationDate("01/01/2030")
                    .issuingAuthority("DMV")
                    .build();

            log.info("{}", license);
        } catch (IllegalArgumentException e) {
            log.error("Error: {}", e.getMessage());
        }
    }
}
