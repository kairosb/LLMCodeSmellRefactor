package org.example.studyregistry;

import java.util.Objects;

public class WeekSetupParameters {
    private String planName;
    private String objectiveTitle;
    private String objectiveDescription;
    private String materialTopic;
    private String materialFormat;
    private String goal;
    private String reminderTitle;
    private String reminderDescription;
    private String mainTaskTitle;
    private String mainHabit;
    private String mainCardStudy;

    private WeekSetupParameters(Builder builder) {
        validateRequiredFields(builder);
        this.planName = builder.planName;
        this.objectiveTitle = builder.objectiveTitle;
        this.goal = builder.goal;
        assignOptionalFields(builder);
    }

    private void validateRequiredFields(Builder builder) {
        if (builder.planName == null || builder.planName.isBlank()) {
            throw new IllegalArgumentException("Plan Name is required.");
        }
        if (builder.objectiveTitle == null || builder.objectiveTitle.isBlank()) {
            throw new IllegalArgumentException("Objective Title is required.");
        }
        if (builder.goal == null || builder.goal.isBlank()) {
            throw new IllegalArgumentException("Goal is required.");
        }
    }

    private void assignOptionalFields(Builder builder) {
        this.objectiveDescription = builder.objectiveDescription;
        this.materialTopic = builder.materialTopic;
        this.materialFormat = builder.materialFormat;
        this.reminderTitle = builder.reminderTitle;
        this.reminderDescription = builder.reminderDescription;
        this.mainTaskTitle = builder.mainTaskTitle;
        this.mainHabit = builder.mainHabit;
        this.mainCardStudy = builder.mainCardStudy;
    }

    public boolean hasValidTopic() {
        return materialTopic != null && !materialTopic.isBlank();
    }

    public String getFormattedGoal() {
        return goal != null ? "Goal: " + goal : "No goal set";
    }

    public String getSummary() {
        return String.format(
            "Plan: %s | Objective: %s | Goal: %s | Topic: %s",
            planName,
            objectiveTitle,
            goal,
            hasValidTopic() ? materialTopic : "Not specified"
        );
    }

    public boolean isComplete() {
        return planName != null && !planName.isBlank() &&
               objectiveTitle != null && !objectiveTitle.isBlank() &&
               goal != null && !goal.isBlank();
    }

    public String getPlanName() { return planName; }
    public String getObjectiveTitle() { return objectiveTitle; }
    public String getObjectiveDescription() { return objectiveDescription; }
    public String getMaterialTopic() { return materialTopic; }
    public String getMaterialFormat() { return materialFormat; }
    public String getGoal() { return goal; }
    public String getReminderTitle() { return reminderTitle; }
    public String getReminderDescription() { return reminderDescription; }
    public String getMainTaskTitle() { return mainTaskTitle; }
    public String getMainHabit() { return mainHabit; }
    public String getMainCardStudy() { return mainCardStudy; }

    public static class Builder {
        private String planName;
        private String objectiveTitle;
        private String objectiveDescription;
        private String materialTopic;
        private String materialFormat;
        private String goal;
        private String reminderTitle;
        private String reminderDescription;
        private String mainTaskTitle;
        private String mainHabit;
        private String mainCardStudy;

        public Builder withPlanName(String planName) {
            this.planName = planName;
            return this;
        }

        public Builder withObjectiveTitle(String objectiveTitle) {
            this.objectiveTitle = objectiveTitle;
            return this;
        }

        public Builder withObjectiveDescription(String objectiveDescription) {
            this.objectiveDescription = objectiveDescription;
            return this;
        }

        public Builder withMaterialTopic(String materialTopic) {
            this.materialTopic = materialTopic;
            return this;
        }

        public Builder withMaterialFormat(String materialFormat) {
            this.materialFormat = materialFormat;
            return this;
        }

        public Builder withGoal(String goal) {
            this.goal = goal;
            return this;
        }

        public Builder withReminderTitle(String reminderTitle) {
            this.reminderTitle = reminderTitle;
            return this;
        }

        public Builder withReminderDescription(String reminderDescription) {
            this.reminderDescription = reminderDescription;
            return this;
        }

        public Builder withMainTaskTitle(String mainTaskTitle) {
            this.mainTaskTitle = mainTaskTitle;
            return this;
        }

        public Builder withMainHabit(String mainHabit) {
            this.mainHabit = mainHabit;
            return this;
        }

        public Builder withMainCardStudy(String mainCardStudy) {
            this.mainCardStudy = mainCardStudy;
            return this;
        }

        public WeekSetupParameters build() {
            return new WeekSetupParameters(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeekSetupParameters that = (WeekSetupParameters) o;
        return Objects.equals(planName, that.planName) &&
               Objects.equals(objectiveTitle, that.objectiveTitle) &&
               Objects.equals(goal, that.goal) &&
               Objects.equals(materialTopic, that.materialTopic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(planName, objectiveTitle, goal, materialTopic);
    }
}
