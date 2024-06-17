package com.showmeyourcode.playground.java.code.pattern.structural;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * The Role Object design pattern is a pattern that suggests modeling context-specific views of an object
 * as separate role objects. These role objects are dynamically attached to and removed from the core object.
 * The resulting composite object structure, consisting of the core and its role objects.
 * <br>
 * Reference: <a href="https://java-design-patterns.com/patterns/role-object/">Role Object</a>
 */
@Slf4j
public class RoleObject {

    private RoleObject() {
    }

    public static void main(String[] args){
        Employee john = new Employee("John");

        Role managerRole = new ManagerRole();
        Role developerRole = new DeveloperRole();

        john.addRole(managerRole);
        john.addRole(developerRole);

        log.info("Role: {}", john.getRole(ManagerRole.class));
        log.info("{} as a Manager:", john.getName());
        john.performRole(ManagerRole.class);

        log.info("{} as a Developer:",john.getName());
        john.performRole(DeveloperRole.class);

        john.removeRole(ManagerRole.class);
        log.info("{} after removing Manager role:", john.getName());
        john.performRole(ManagerRole.class);
    }
}

interface Role {
    void performRole();
}

@Slf4j
class ManagerRole implements Role {
    @Override
    public void performRole() {
        log.info("Performing Manager tasks");
    }
}

@Slf4j
class DeveloperRole implements Role {
    @Override
    public void performRole() {
        log.info("Performing Developer tasks");
    }
}

@Slf4j
class Employee {
    @Getter
    private final String name;
    private final Map<Class<? extends Role>, Role> roles = new HashMap<>();

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
            log.warn("{} does not have role: {}", name, roleClass.getSimpleName());
        }
    }
}
