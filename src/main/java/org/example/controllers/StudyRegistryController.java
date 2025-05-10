package org.example.controllers;

import org.example.studymaterial.AudioReference;
import org.example.studymaterial.Reference;
import org.example.studymaterial.TextReference;
import org.example.studymaterial.VideoReference;
import org.example.studyregistry.*;

import java.time.LocalDateTime;
import java.util.*;

import static org.example.controllers.MainController.getInput;
import static org.example.controllers.MainController.validateInput;

public class StudyRegistryController {
    StudyTaskManager studyTaskManager = StudyTaskManager.getStudyTaskManager();
    StudyMaterial studyMaterial = StudyMaterial.getStudyMaterial();
    private Map<String, Runnable> actions = new HashMap<>();

    public StudyRegistryController() {
        assignActions();
    }

    static String getInput(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    void assignActions(){
        actions.put("1", this::handleAddStudyGoal);
        actions.put("2", this::handleAddStudyMaterial);
        actions.put("3", this::handleAddStudyObjective);
        actions.put("4", this::handleAddStudyPlan);
        actions.put("5", this::handleSetUpWeek);
        actions.put("6", this::handleGetWeekResponsibilities);
    }

    private void handleMethodHeader(String header){
        System.out.println("~~~~" + header + "~~~~\n");
    }

    private Task getStudyTaskInfo(){
        System.out.println("Type the following info: title, description, author \n");
        return new Task(getInput(), getInput(), getInput(), LocalDateTime.now());
    }

    private void handleAddStudyTask(){
        studyTaskManager.addRegistry(getStudyTaskInfo());
    }

    private void handleSetObjective(StudyObjective objective) {
        handleMethodHeader("(Study Objective Edit)");
        ObjectiveSetParams params = collectObjectiveParams();
        try {
            objective.handleSetObjective(params);
            System.out.println("Study Objective updated successfully. ID: " + params.getId());
        } catch (IllegalArgumentException e) {
            System.out.println("Error setting the objective: " + e.getMessage());
        }
    }

    private ObjectiveSetParams collectObjectiveParams() {
        try {
            return new ObjectiveSetParams(
                Long.parseLong(getInput()),
                Integer.parseInt(getInput()),
                Integer.parseInt(getInput()),
                Integer.parseInt(getInput()),
                Integer.parseInt(getInput()),
                Integer.parseInt(getInput()),
                getInput(),
                getInput(),
                getInput(),
                getInput(),
                getInput(),
                getInput(),
                getInput(),
                Double.parseDouble(getInput()),
                Boolean.parseBoolean(getInput())
            );
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error converting input: " + e.getMessage());
        }
    }

    private StudyObjective getStudyObjectiveInfo(){
        handleMethodHeader("(Study Objective Creation)");
        System.out.println("Type the following info: title, description \n");
        String title = getInput();
        String description = getInput();
        StudyObjective studyObjective = new StudyObjective(title, description);
        handleSetObjective(studyObjective);
        studyTaskManager.addRegistry(studyObjective);
        return studyObjective;
    }

    private StudyPlan getStudyPlanInfo(){
        handleMethodHeader("(Study Plan Creation)");
        StudyObjective studyObjective = getStudyObjectiveInfo();
        if (studyObjective == null) {
            return null;
        }
        StudyPlan plan = new StudyPlan(getInput(), studyObjective, new ArrayList<>());
        studyTaskManager.addRegistry(plan);
        return plan;
    }



    private StudyGoal getStudyGoalInfo(){
        handleMethodHeader("(Study Goal Creation)");
        StudyPlan studyPlan = getStudyPlanInfo();
        if (studyPlan == null) {
            return null;
        }
        handleSetSteps(studyPlan);
        return new StudyGoal(getInput(), studyPlan.getObjective(), studyPlan);
    }

    private void handleAddStudyGoal(){
        StudyGoal goal = getStudyGoalInfo();
        if (goal != null) {
            studyTaskManager.addRegistry(goal);
        }
    }

    private AudioReference addAudioReference(){
        handleMethodHeader("(Audio Reference Creation)");
        AudioReference audioReference = new AudioReference(AudioReference.audioQualityAdapter(getInput()));
        editAudioReference(audioReference);
        return audioReference;
    }

    private void editAudioReference(AudioReference audioReference){
        try {
            audioReference.editAudio(
                AudioReference.audioQualityAdapter(getInput()),
                Boolean.parseBoolean(getInput()),
                new AudioReference.AudioMetadata(
                    getInput(),
                    getInput(),
                    getInput(),
                    getInput(),
                    getInput(),
                    getInput()
                ),
                new AudioReference.AudioStats(
                    Integer.parseInt(getInput()),
                    Integer.parseInt(getInput()),
                    Integer.parseInt(getInput())
                )
            );
        } catch (NumberFormatException e) {
            System.out.println("Error editing audio: " + e.getMessage());
        }
    }

    private void handleAddStudyMaterial(){
        Reference reference = createReferenceFromInput();
        if (reference != null) {
            studyMaterial.addReference(reference);
        }
    }

    private Reference createReferenceFromInput() {
        handleMethodHeader("(Study Material Creation)");
        String type = getInput().toLowerCase();
        return switch (type) {
            case "audio" -> addAudioReference();
            case "video" -> new VideoReference(
                Boolean.parseBoolean(getInput()),
                getInput(),
                getInput(),
                getInput(),
                getInput(),
                getInput(),
                getInput()
            );
            case "text" -> new TextReference(
                getInput(),
                getInput(),
                Integer.parseInt(getInput()),
                getInput(),
                getInput()
            );
            default -> {
                System.out.println("Invalid type. Must be AUDIO, VIDEO, or TEXT.");
                yield null;
            }
        };
    }

    public void handleRegistryInput(){
        try {
            while(true){
                controllerOptions();
                String response = validateInput(actions);
                if(response == null) {
                    return;
                }
                actions.get(response).run();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleAddStudyObjective() {
        StudyObjective studyObjective = getStudyObjectiveInfo();
        if (studyObjective != null) {
            System.out.println("Study Objective added successfully.");
        }
    }

    private void handleAddStudyPlan() {
        StudyPlan plan = getStudyPlanInfo();
        if (plan != null) {
            System.out.println("Study Plan added successfully.");
        }
    }
    private void handleSetSteps(StudyPlan studyPlan){
        handleMethodHeader("(Study Plan Edit)");
        AssignStepsParameters params = collectAssignStepsParameters();
        try {
            studyPlan.assignSteps(params);
        } catch (IllegalArgumentException e) {
            System.out.println("Error assigning steps: " + e.getMessage());
        }
    }

    private AssignStepsParameters collectAssignStepsParameters() {
        try {
            return new AssignStepsParameters(
                new AssignStepsParameters.StepDetails(
                    getInput(),
                    getInput(),
                    getInput(),
                    getInput(),
                    getInput()
                ),
                new AssignStepsParameters.PlanDetails(
                    getInput(),
                    getInput(),
                    getInput(),
                    getInput(),
                    Integer.parseInt(getInput()),
                    Boolean.parseBoolean(getInput()),
                    LocalDateTime.now(),
                    LocalDateTime.now().plusDays(Long.parseLong(getInput()))
                )
            );
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error converting input: " + e.getMessage());
        }
    }

    private void handleSetUpWeek() {
        handleMethodHeader("(Week Setup)");
        WeekSetupParameters params = collectWeekSetupParameters();
        studyTaskManager.setUpWeek(params);
        System.out.println("Week setup completed successfully.");
    }

    private WeekSetupParameters collectWeekSetupParameters() {
        return new WeekSetupParameters.Builder()
            .withPlanName(getInput())
            .withObjectiveTitle(getInput())
            .withObjectiveDescription(getInput())
            .withMaterialTopic(getInput())
            .withMaterialFormat(getInput())
            .withGoal(getInput())
            .withReminderTitle(getInput())
            .withReminderDescription(getInput())
            .withMainTaskTitle(getInput())
            .withMainHabit(getInput())
            .withMainCardStudy(getInput())
            .build();
    }

    private void handleGetWeekResponsibilities(){
        List<String> responsibilities = studyTaskManager.getWeekResponsibilities();
        System.out.println("Week Responsibilities: " + String.join(", ", responsibilities));
    }


    public static void controllerOptions(){
        System.out.println("""
                0 - return
                1 - add study task
                2 - add study goal
                3 - add study material (audio, video, text)
                4 - add study objective
                5 - add study plan
                6 - set up week
                7 - get week responsibilities
               """);
    }
}
