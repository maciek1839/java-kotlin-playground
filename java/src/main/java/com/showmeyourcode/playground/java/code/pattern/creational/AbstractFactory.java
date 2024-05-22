package com.showmeyourcode.playground.java.code.pattern.creational;

interface DriverLicense {
    void displayLicenseDetails();
}

interface DriverLicenseFactory {
    DriverLicense createDriverLicense(String licenseNumber, String name);
}

class USADriverLicense implements DriverLicense {
    private String licenseNumber;
    private String name;

    public USADriverLicense(String licenseNumber, String name) {
        this.licenseNumber = licenseNumber;
        this.name = name;
    }

    @Override
    public void displayLicenseDetails() {
        System.out.println("USA Driver License: [License Number: " + licenseNumber + ", Name: " + name + "]");
    }
}

class GermanyDriverLicense implements DriverLicense {
    private String licenseNumber;
    private String name;

    public GermanyDriverLicense(String licenseNumber, String name) {
        this.licenseNumber = licenseNumber;
        this.name = name;
    }

    @Override
    public void displayLicenseDetails() {
        System.out.println("Germany Driver License: [License Number: " + licenseNumber + ", Name: " + name + "]");
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

public class AbstractFactory {

    public static void main(){
        DriverLicenseFactory usaFactory = new USADriverLicenseFactory();
        usaFactory.createDriverLicense("123456789", "John Doe").displayLicenseDetails();

        DriverLicenseFactory germanyFactory = new GermanyDriverLicenseFactory();
        germanyFactory.createDriverLicense("987654321", "Hans Müller").displayLicenseDetails();
    }
}
