package ch.cern.todo.presentation.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class TaskDTO {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime deadline;
    private CategoryDTO category;

    public TaskDTO() {
    }

    public TaskDTO(Long id, String name, String description, LocalDateTime deadline, CategoryDTO category) {
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
        TaskDTO taskDTO = (TaskDTO) o;
        return Objects.equals(id, taskDTO.id) && Objects.equals(name, taskDTO.name) && Objects.equals(description, taskDTO.description) && Objects.equals(deadline, taskDTO.deadline) && Objects.equals(category, taskDTO.category);
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

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }
}
