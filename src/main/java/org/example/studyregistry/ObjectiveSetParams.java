package org.example.studyregistry;

import java.util.List;

public class ObjectiveSetParams {
    private Long id;
    private Integer priority;
    private Integer practicedDays;
    private int day;
    private int month;
    private int year;
    private String name;
    private String title;
    private String description;
    private String topic;
    private String objectiveInOneLine;
    private String objectiveFullDescription;
    private String motivation;
    private Double duration;
    private boolean isActive;

    public static ObjectiveSetParams fromProperties(List<Integer> intProperties, List<String> stringProperties, Double duration, boolean isActive) {
        Long id = Long.valueOf(intProperties.get(0));
        Integer priority = intProperties.get(1);
        Integer practicedDays = intProperties.get(2);
        int day = intProperties.get(3);
        int month = intProperties.get(4);
        int year = intProperties.get(5);

        String name = stringProperties.get(0);
        String title = stringProperties.get(1);
        String description = stringProperties.get(2);
        String topic = stringProperties.get(3);
        String objectiveInOneLine = stringProperties.get(4);
        String objectiveFullDescription = stringProperties.get(5);
        String motivation = stringProperties.get(6);

        return new ObjectiveSetParams(id, priority, practicedDays, day, month, year, name, title, description, topic, objectiveInOneLine, objectiveFullDescription, motivation, duration, isActive);
    }
    
    public ObjectiveSetParams(Long id, Integer priority, Integer practicedDays, int day, int month, int year,
                              String name, String title, String description, String topic,
                              String objectiveInOneLine, String objectiveFullDescription,
                              String motivation, Double duration, boolean isActive) {
        this.id = id;
        this.priority = priority;
        this.practicedDays = practicedDays;
        this.day = day;
        this.month = month;
        this.year = year;
        this.name = name;
        this.title = title;
        this.description = description;
        this.topic = topic;
        this.objectiveInOneLine = objectiveInOneLine;
        this.objectiveFullDescription = objectiveFullDescription;
        this.motivation = motivation;
        this.duration = duration;
        this.isActive = isActive;
    }

    // Getters
    public Long getId() { return id; }
    public Integer getPriority() { return priority; }
    public Integer getPracticedDays() { return practicedDays; }
    public int getDay() { return day; }
    public int getMonth() { return month; }
    public int getYear() { return year; }
    public String getName() { return name; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getTopic() { return topic; }
    public String getObjectiveInOneLine() { return objectiveInOneLine; }
    public String getObjectiveFullDescription() { return objectiveFullDescription; }
    public String getMotivation() { return motivation; }
    public Double getDuration() { return duration; }
    public boolean isActive() { return isActive; }
}
