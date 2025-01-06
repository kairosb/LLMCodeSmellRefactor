package org.example.studyplanner;

import java.text.MessageFormat;

public class ToDo implements PlannerMaterial {
    private Integer id;
    private String title;
    private String description;
    private int priority;

    public ToDo(Integer id, String title, String description, int priority) {
        validateAndSetId(id);
        validateAndSetTitle(title);
        validateAndSetDescription(description);
        validateAndSetPriority(priority);
    }

    @Override
    public String toString() {
        return MessageFormat.format("[(Priority:{3}) ToDo {0}: {1}, {2}]", id, title, description, priority);
    }

    public Integer getId() {
        return id;
    }

    private void validateAndSetId(Integer id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("ID deve ser um número positivo");
        }
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    private void validateAndSetTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Título não pode ser vazio");
        }
        this.title = title.trim();
    }

    public String getDescription() {
        return description;
    }

    private void validateAndSetDescription(String description) {
        if (description == null) {
            throw new IllegalArgumentException("Descrição não pode ser nula");
        }
        this.description = description.trim();
    }

    public int getPriority() {
        return priority;
    }

    private void validateAndSetPriority(int priority) {
        if (priority < 1 || priority > 5) {
            throw new IllegalArgumentException("Prioridade deve estar entre 1 e 5");
        }
        this.priority = priority;
    }

    public void updateToDo(String newTitle, String newDescription, int newPriority) {
        validateAndSetTitle(newTitle);
        validateAndSetDescription(newDescription);
        validateAndSetPriority(newPriority);
    }

    public boolean isHighPriority() {
        return priority <= 2;
    }

    public boolean isMediumPriority() {
        return priority == 3;
    }

    public boolean isLowPriority() {
        return priority >= 4;
    }
}