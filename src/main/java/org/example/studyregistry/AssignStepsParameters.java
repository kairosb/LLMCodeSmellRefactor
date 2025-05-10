package org.example.studyregistry;

import java.time.LocalDateTime;

public class AssignStepsParameters {

    private final StepDetails stepDetails;
    private final PlanDetails planDetails;

    public AssignStepsParameters(StepDetails stepDetails, PlanDetails planDetails) {
        if (stepDetails == null || planDetails == null) {
            throw new IllegalArgumentException("StepDetails and PlanDetails cannot be null.");
        }
        this.stepDetails = stepDetails;
        this.planDetails = planDetails;
    }

    public StepDetails getStepDetails() {
        return stepDetails;
    }

    public PlanDetails getPlanDetails() {
        return planDetails;
    }

    public static class StepDetails {
        private final String firstStep;
        private final String resetStudyMechanism;
        private final String consistentStep;
        private final String seasonalSteps;
        private final String basicSteps;

        public StepDetails(String firstStep, String resetStudyMechanism, String consistentStep,
                           String seasonalSteps, String basicSteps) {
            if (firstStep == null || resetStudyMechanism == null || consistentStep == null ||
                seasonalSteps == null || basicSteps == null) {
                throw new IllegalArgumentException("Step details cannot have null fields.");
            }
            this.firstStep = firstStep;
            this.resetStudyMechanism = resetStudyMechanism;
            this.consistentStep = consistentStep;
            this.seasonalSteps = seasonalSteps;
            this.basicSteps = basicSteps;
        }

        public String getFirstStep() {
            return firstStep;
        }

        public String getResetStudyMechanism() {
            return resetStudyMechanism;
        }

        public String getConsistentStep() {
            return consistentStep;
        }

        public String getSeasonalSteps() {
            return seasonalSteps;
        }

        public String getBasicSteps() {
            return basicSteps;
        }

        public String summarizeSteps() {
            return String.format("First Step: %s, Reset Mechanism: %s, Consistent Step: %s", firstStep, resetStudyMechanism, consistentStep);
        }
    }

    public static class PlanDetails {
        private final String mainObjectiveTitle;
        private final String mainGoalTitle;
        private final String mainMaterialTopic;
        private final String mainTask;
        private final Integer numberOfSteps;
        private final boolean isImportant;
        private final LocalDateTime startDate;
        private final LocalDateTime endDate;

        public PlanDetails(String mainObjectiveTitle, String mainGoalTitle, String mainMaterialTopic, String mainTask,
                           Integer numberOfSteps, boolean isImportant, LocalDateTime startDate, LocalDateTime endDate) {
            if (mainObjectiveTitle == null || mainGoalTitle == null || mainMaterialTopic == null || mainTask == null ||
                startDate == null || endDate == null) {
                throw new IllegalArgumentException("Plan details cannot have null fields.");
            }
            this.mainObjectiveTitle = mainObjectiveTitle;
            this.mainGoalTitle = mainGoalTitle;
            this.mainMaterialTopic = mainMaterialTopic;
            this.mainTask = mainTask;
            this.numberOfSteps = numberOfSteps;
            this.isImportant = isImportant;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public String getMainObjectiveTitle() {
            return mainObjectiveTitle;
        }

        public String getMainGoalTitle() {
            return mainGoalTitle;
        }

        public String getMainMaterialTopic() {
            return mainMaterialTopic;
        }

        public String getMainTask() {
            return mainTask;
        }

        public Integer getNumberOfSteps() {
            return numberOfSteps;
        }

        public boolean isImportant() {
            return isImportant;
        }

        public LocalDateTime getStartDate() {
            return startDate;
        }

        public LocalDateTime getEndDate() {
            return endDate;
        }

        public String planSummary() {
            return String.format("Objective: %s, Goal: %s, Material: %s", mainObjectiveTitle, mainGoalTitle, mainMaterialTopic);
        }
    }
}
