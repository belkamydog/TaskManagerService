package ru.task.TaskManager.models;

import jakarta.persistence.*;
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
@Table (name = "users")
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    private String login;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role = Role.User;
    boolean enabled = true;

    public User(String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }
}
