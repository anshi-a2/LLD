package com.auth.login.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue
    private String userid;
    private String username;
    private String passowrd;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;
}
