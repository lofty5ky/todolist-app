package me.example.todolistapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "todos")
public class Todo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;

    private boolean isComplete;

    private Instant createdTime;

    private Instant updatedTime;

    @Override
    public String toString() {
        return String.format("Todo{id=%d, description='%s', isComplete='%s', createdTime='%s', updatedTime='%s'}",
                id, description, isComplete, createdTime, updatedTime);
    }
}
