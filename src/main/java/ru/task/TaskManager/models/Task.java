package ru.task.TaskManager.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    private String status;
    private String comments;
}
