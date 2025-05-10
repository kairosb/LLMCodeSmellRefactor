package org.example.studyplanner;

import java.text.MessageFormat;

public class ToDo implements PlannerMaterial {
    private static final int MIN_PRIORITY = 1;
    private static final int MAX_PRIORITY = 5;

    private Integer id;
    private String title;
    private String description;
    private int priority;

    public ToDo(Integer id, String title, String description, int priority) {
        setId(id);
        setTitle(title);
        setDescription(description);
        setPriority(priority);
    }

    public void increasePriority() {
        if (priority < MAX_PRIORITY) {
            priority++;
        }
    }

    public void decreasePriority() {
        if (priority > MIN_PRIORITY) {
            priority--;
        }
    }

    public boolean isHighPriority() {
        return priority >= 4;
    }

    public boolean isLowPriority() {
        return priority <= 2;
    }

    @Override
    public String toString() {
        return MessageFormat.format("[(Priority:{3}) ToDo {0}: {1}, {2}]",
                id, title, description, priority);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        this.title = title.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null) {
            throw new IllegalArgumentException("Description cannot be null");
        }
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        if (priority < MIN_PRIORITY || priority > MAX_PRIORITY) {
            throw new IllegalArgumentException(
                    "Priority must be between " + MIN_PRIORITY + " and " + MAX_PRIORITY);
        }
        this.priority = priority;
    }
}