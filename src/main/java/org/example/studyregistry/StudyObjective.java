package org.example.studyregistry;

import java.time.LocalDateTime;

public class StudyObjective extends Registry {
    private CoreDetails coreDetails;
    private AdditionalDetails additionalDetails;

    public StudyObjective(String title, String description) {
        this.coreDetails = new CoreDetails(title, description, LocalDateTime.now(), 0);
        this.additionalDetails = new AdditionalDetails();

        setName(title); 
        setId(1L); 
        setPriority(2); 
        setActive(false); 
    }

    public StudyObjective(CoreDetails coreDetails, AdditionalDetails additionalDetails) {
        this.coreDetails = coreDetails;
        this.additionalDetails = additionalDetails;

        setName(coreDetails.getTitle());
        setId(1L);
        setPriority(2);
        setActive(false);
    }

    public CoreDetails getCoreDetails() {
        return coreDetails;
    }

    public AdditionalDetails getAdditionalDetails() {
        return additionalDetails;
    }

    public void handleSetObjective(ObjectiveSetParams params) {
        validateParams(params);
        this.coreDetails.updateDetails(params);
        this.additionalDetails.updateDetails(params);
    }

    private void validateParams(ObjectiveSetParams params) {
        if (params == null) {
            throw new IllegalArgumentException("ObjectiveSetParams cannot be null.");
        }
        if (params.getTitle() == null || params.getTitle().isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or blank.");
        }
        if (params.getDescription() == null || params.getDescription().isBlank()) {
            throw new IllegalArgumentException("Description cannot be null or blank.");
        }
        if (params.getDuration() == null || params.getDuration() <= 0) {
            throw new IllegalArgumentException("Duration must be greater than zero.");
        }
        if (params.getPracticedDays() == null || params.getPracticedDays() < 0) {
            throw new IllegalArgumentException("Practiced days cannot be null or negative.");
        }
    }

    public static class CoreDetails {
        private String title;
        private String description;
        private String topic;
        private Integer practicedDays;
        private LocalDateTime startDate;

        public CoreDetails(String title, String description, LocalDateTime startDate, Integer practicedDays) {
            this.title = title;
            this.description = description;
            this.startDate = startDate != null ? startDate : LocalDateTime.now();
            this.practicedDays = practicedDays != null ? practicedDays : 0;
        }

        public void updateDetails(ObjectiveSetParams params) {
            this.title = params.getTitle();
            this.description = params.getDescription();
            this.topic = params.getTopic();
            this.practicedDays = params.getPracticedDays();
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public String getTopic() {
            return topic;
        }

        public Integer getPracticedDays() {
            return practicedDays;
        }

        public LocalDateTime getStartDate() {
            return startDate;
        }

        public String getSummary() {
            return String.format("Title: %s, Description: %s, Topic: %s", title, description, topic);
        }
    }

    public static class AdditionalDetails {
        private Double duration;
        private String objectiveInOneLine;
        private String objectiveFullDescription;
        private String motivation;

        public AdditionalDetails() {
            this.duration = 0.0;
            this.objectiveInOneLine = "";
            this.objectiveFullDescription = "";
            this.motivation = "";
        }

        public void updateDetails(ObjectiveSetParams params) {
            this.duration = params.getDuration();
            this.objectiveInOneLine = params.getObjectiveInOneLine();
            this.objectiveFullDescription = params.getObjectiveFullDescription();
            this.motivation = params.getMotivation();
        }

        public Double getDuration() {
            return duration;
        }

        public String getObjectiveInOneLine() {
            return objectiveInOneLine;
        }

        public String getObjectiveFullDescription() {
            return objectiveFullDescription;
        }

        public String getMotivation() {
            return motivation;
        }

        public String generateMotivationReport() {
            return String.format("Motivation: %s, Objective: %s", motivation, objectiveInOneLine);
        }
    }
}
