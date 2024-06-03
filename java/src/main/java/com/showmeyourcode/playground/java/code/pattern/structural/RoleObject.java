package com.showmeyourcode.playground.java.code.pattern.structural;

import java.util.HashMap;
import java.util.Map;

interface Role {
    void performRole();
}

class ManagerRole implements Role {
    @Override
    public void performRole() {
        System.out.println("Performing Manager tasks");
    }
}

class DeveloperRole implements Role {
    @Override
    public void performRole() {
        System.out.println("Performing Developer tasks");
    }
}

class Employee {
    private String name;
    private Map<Class<? extends Role>, Role> roles = new HashMap<>();

    public Employee(String name) {
        this.name = name;
    }

    public void addRole(Role role) {
        roles.put(role.getClass(), role);
    }

    public void removeRole(Class<? extends Role> roleClass) {
        roles.remove(roleClass);
    }

    public Role getRole(Class<? extends Role> roleClass) {
        return roles.get(roleClass);
    }

    public void performRole(Class<? extends Role> roleClass) {
        Role role = roles.get(roleClass);
        if (role != null) {
            role.performRole();
        } else {
            System.out.println(name + " does not have role: " + roleClass.getSimpleName());
        }
    }

    public String getName() {
        return name;
    }
}

public class RoleObject {

    public static void main(){
        Employee john = new Employee("John");

        Role managerRole = new ManagerRole();
        Role developerRole = new DeveloperRole();

        john.addRole(managerRole);
        john.addRole(developerRole);

        System.out.println(john.getName() + " as a Manager:");
        john.performRole(ManagerRole.class);

        System.out.println(john.getName() + " as a Developer:");
        john.performRole(DeveloperRole.class);

        john.removeRole(ManagerRole.class);
        System.out.println(john.getName() + " after removing Manager role:");
        john.performRole(ManagerRole.class);
    }
}
