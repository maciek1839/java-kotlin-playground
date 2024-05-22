package com.showmeyourcode.playground.java.code.pattern.creational;

// Reference: https://softwareengineering.stackexchange.com/questions/430462/should-validation-logic-be-inside-a-factory-method-or-inside-the-objects-constr
public class FactoryMethod {

    static class DriverLicense {
        private static final String DRIVER_LICENSE_REGEX = "[A-Z]{2}[0-9]{6}";
        private final String license;

        private DriverLicense(String name) {
            this.license = name;
        }

        public static DriverLicense create(String license) {
            // do necessary validation before
            if(license == null || license.isEmpty() || !license.matches(DRIVER_LICENSE_REGEX)){
                throw new IllegalArgumentException("Invalid license");
            }
            return new DriverLicense(license);
        }

        public String getLicense() {
            return license;
        }
    }

    public static void main() {
        DriverLicense driveLicense = DriverLicense.create("KK123000");
        driveLicense.getLicense();
    }
}
