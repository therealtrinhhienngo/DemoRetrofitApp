package com.example.demoretrofitapp.Model;

public class Person {
    String id, name, role;

    public Person(String id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public Person() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
