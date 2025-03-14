package ru.task.TaskManager.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue
    private long id;
    private String comment;
    @ManyToOne
    @JoinColumn (name = "user_id")
    private User owner;
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;
}
