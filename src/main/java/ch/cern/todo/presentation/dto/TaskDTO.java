package ch.cern.todo.presentation.dto;

import java.time.LocalDateTime;

public class TaskDTO {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime deadline;
    private TaskCategoryDTO category;

    public TaskDTO() {
    }

    public TaskDTO(Long id, String name, String description, LocalDateTime deadline, TaskCategoryDTO category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.category = category;
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

    public TaskCategoryDTO getCategory() {
        return category;
    }

    public void setCategory(TaskCategoryDTO category) {
        this.category = category;
    }
}
