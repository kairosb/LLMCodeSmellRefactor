package org.example.controllers;

import java.util.Map;
import java.util.Scanner;

public class MainController {
    private StudyCardsController studyCardsController;
    private StudyPlannerController studyPlannerController;
    private StudyRegistryController studyRegistryController;
    private StudySearchController studySearchController;

    public MainController(StudyCardsController studyCardsController, StudyPlannerController studyPlannerController,
                          StudyRegistryController studyRegistryController, StudySearchController studySearchController){
        this.studyCardsController = studyCardsController;
        this.studyPlannerController = studyPlannerController;
        this.studyRegistryController = studyRegistryController;
        this.studySearchController = studySearchController;
    }

    static String getInput(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static String validateInput(Map<String, Runnable> actions) throws Exception {
        String input = getInput();
        if(input.equals("0")){return null;}
        Runnable ac = actions.get(input);
        if(ac == null) throw new Exception("Invalid action");
        return input;
    }

    public void handleUserInput(String input){
        switch (input){
            case "1" -> studyCardsController.handleCardsInput();
            case "2" -> studyPlannerController.handlePlannerInput();
            case "3" -> studyRegistryController.handleRegistryInput();
            case "4" -> studySearchController.handleSearchInput();
            default -> {
                return;
            }
        }
    }
}
