package com.showmeyourcode.playground.java.code.pattern.creational;

import lombok.extern.slf4j.Slf4j;

public class AbstractFactory {

    private AbstractFactory() {
    }

    public static void main(String[] args){
        DriverLicenseFactory usaFactory = new USADriverLicenseFactory();
        usaFactory.createDriverLicense("123456789", "John Doe").displayLicenseDetails();

        DriverLicenseFactory germanyFactory = new GermanyDriverLicenseFactory();
        germanyFactory.createDriverLicense("987654321", "Hans Müller").displayLicenseDetails();
    }
}

interface DriverLicense {
    void displayLicenseDetails();
}

interface DriverLicenseFactory {
    DriverLicense createDriverLicense(String licenseNumber, String name);
}

@Slf4j
class USADriverLicense implements DriverLicense {
    private final String licenseNumber;
    private final String name;

    public USADriverLicense(String licenseNumber, String name) {
        this.licenseNumber = licenseNumber;
        this.name = name;
    }

    @Override
    public void displayLicenseDetails() {
        log.info("USA Driver License: [License Number: {}, Name: {}]", licenseNumber, name);
    }
}

@Slf4j
class GermanyDriverLicense implements DriverLicense {
    private final String licenseNumber;
    private final String name;

    public GermanyDriverLicense(String licenseNumber, String name) {
        this.licenseNumber = licenseNumber;
        this.name = name;
    }

    @Override
    public void displayLicenseDetails() {
        log.info("Germany Driver License: [License Number: {}, Name: {}]", licenseNumber, name);
    }
}

// ============= factories =============
class USADriverLicenseFactory implements DriverLicenseFactory {
    @Override
    public DriverLicense createDriverLicense(String licenseNumber, String name) {
        return new USADriverLicense(licenseNumber, name);
    }
}

class GermanyDriverLicenseFactory implements DriverLicenseFactory {
    @Override
    public DriverLicense createDriverLicense(String licenseNumber, String name) {
        return new GermanyDriverLicense(licenseNumber, name);
    }
}
