package org.example.studyregistry;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StudyGoal extends Registry {
    private String goal;
    private List<String> goalRequirements;
    private Boolean isCompleted;
    private LocalDateTime createdDate;
    private Double goalCompletion;
    private StudyObjective studyObjective;
    private StudyPlan studyPlan;
    private String summary;

    public StudyGoal(String name, StudyObjective objective, StudyPlan plan) {
        this.name = name;
        this.studyObjective = objective;
        this.studyPlan = plan;
        goalRequirements = new ArrayList<>();
    }

    public void editActiveCompleted(boolean active, boolean completed) {
        this.isActive = active;
        this.isCompleted = completed;
    }

    public String setGoalSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("Goal Summary:\n\n");
        appendActiveGoal(summary);
        appendCompletedGoal(summary);
        appendStudyObjective(summary);
        appendStudyPlan(summary);
        appendGoalRequirements(summary);
        return summary.toString();
    }

    private void appendActiveGoal(StringBuilder summary) {
        if (this.isActive) {
            summary.append("Active Goal:\n").append(goal).append("\n\n");
        }
    }

    private void appendCompletedGoal(StringBuilder summary) {
        if (this.isCompleted) {
            summary.append("Completed Goal:\n").append(goal).append("\n\n");
        }
    }

    private void appendStudyObjective(StringBuilder summary) {
        if (studyObjective != null) {
            summary.append("Study Objective:\n")
                   .append(studyObjective.getName()).append("\n")
                   .append(studyObjective.getCoreDetails().getDescription()).append("\n\n");
        }
    }

    private void appendStudyPlan(StringBuilder summary) {
        if (studyPlan != null) {
            summary.append("Study Plan:\n")
                   .append(studyPlan.getName()).append("\n\n");
            for (String step : studyPlan.getSteps()) {
                summary.append("Step: ").append(step).append("\n");
            }
            summary.append("\n");
        }
    }

    private void appendGoalRequirements(StringBuilder summary) {
        summary.append("Goal Requirements:\n");
        for (String requirement : goalRequirements) {
            summary.append(requirement).append("\n");
        }
        summary.append("\n");
    }

    public void addRequirement(String requirement) {
        this.goalRequirements.add(requirement);
    }

    public void resetRequirements() {
        this.goalRequirements.clear();
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void toggleIsCompleted() {
        this.isCompleted = !this.isCompleted;
    }

    public LocalDateTime getLimitDate() {
        return createdDate;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }
}