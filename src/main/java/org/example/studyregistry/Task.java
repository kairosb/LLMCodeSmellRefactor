package org.example.studyregistry;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task extends Registry {
    private String title;
    private String description;
    private String author;
    private LocalDateTime date;

    public Task(String title, String description, String author, LocalDateTime date) {
        setTitle(title);
        setDescription(description);
        setAuthor(author);
        setDate(date);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        this.title = title;
        this.name = title; // Assuming name is a field in the superclass Registry
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (author == null || author.isEmpty()) {
            throw new IllegalArgumentException("Author cannot be null or empty");
        }
        this.author = author;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        this.date = date;
    }

    // Método adicional para encapsular lógica de negócios
    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return date.format(formatter);
    }

    // Método adicional para encapsular lógica de negócios
    public boolean isTaskDue() {
        return LocalDateTime.now().isAfter(date);
    }

    // Método adicional para encapsular lógica de negócios
    public void postponeTask(int days) {
        this.date = this.date.plusDays(days);
    }

    // Método adicional para encapsular lógica de negócios
    public String getSummary() {
        return String.format("Task: %s\\nDescription: %s\\nAuthor: %s\\nDue Date: %s",
                getTitle(), getDescription(), getAuthor(), getFormattedDate());
    }

    // Método adicional para encapsular lógica de negócios
    public boolean isAuthor(String authorName) {
        return this.author.equalsIgnoreCase(authorName);
    }
}
