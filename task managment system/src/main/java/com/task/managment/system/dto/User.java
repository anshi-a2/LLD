package com.task.managment.system.dto;

import java.util.UUID;

public class User {
    private final String id;
    private String name;
    private String email;

    public User(String name, String email){
        this.id= UUID.randomUUID().toString();
        this.name=name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
