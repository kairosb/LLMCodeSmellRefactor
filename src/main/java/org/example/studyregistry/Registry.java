package org.example.studyregistry;

public abstract class Registry {
    Long id;
    String name;
    Integer priority;
    boolean isActive;

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
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank.");
        }
        this.name = name;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        if (priority == null || priority < 1 || priority > 10) {
            throw new IllegalArgumentException("Priority must be between 1 and 10.");
        }
        this.priority = priority;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public void toggleActiveStatus() {
        this.isActive = !this.isActive;
    }

    public String getSummary() {
        return String.format("Registry [ID: %d, Name: %s, Priority: %d, Active: %b]",
                id, name, priority, isActive);
    }

    public boolean hasHighPriority() {
        return priority != null && priority > 7;
    }
}
