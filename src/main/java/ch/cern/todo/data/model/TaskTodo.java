package ch.cern.todo.data.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "TASKS")
public class TaskTodo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String name;
    private String description;
    @NotNull
    private LocalDateTime deadline;
    @ManyToOne
    @NotNull
    private Category category;

    public TaskTodo() {
    }

    public TaskTodo(Long id, String name, String description, LocalDateTime deadline, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskTodo taskTodo = (TaskTodo) o;
        return id.equals(taskTodo.id) && name.equals(taskTodo.name) && Objects.equals(description, taskTodo.description) && deadline.equals(taskTodo.deadline) && category.equals(taskTodo.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, deadline, category);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
