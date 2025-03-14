package ru.task.TaskManager.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

enum Role {
    Admin,
    User
}

@Entity
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    private int id;
    private String login;
    private String password;
    private String email;
    private Role role;
    boolean enabled = true;
}
