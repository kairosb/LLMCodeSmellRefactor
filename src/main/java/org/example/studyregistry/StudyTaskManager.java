package org.example.studyregistry;

import org.example.studymaterial.Reference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudyTaskManager {
    private static StudyTaskManager instance;
    private StudyMaterial studyMaterial = StudyMaterial.getStudyMaterial();
    List<Registry> registryList;
    List<String> weekResponsibilities = List.of();

    private StudyTaskManager(){
        this.registryList = new ArrayList<Registry>();
    }

    public static StudyTaskManager getStudyTaskManager(){
        if (instance == null) {
            instance = new StudyTaskManager();
        }
        return instance;
    }

    public List<String> getWeekResponsibilities() {
        return weekResponsibilities;
    }

    public void setUpWeek(WeekSetupParameters params){
        this.weekResponsibilities = new ArrayList<>();
        this.weekResponsibilities.addAll(Arrays.asList(
            params.getPlanName(),
            params.getObjectiveTitle(),
            params.getObjectiveDescription(),
            params.getMaterialTopic(),
            params.getMaterialFormat(),
            params.getGoal(),
            params.getReminderTitle(),
            params.getReminderDescription(),
            params.getMainTaskTitle(),
            params.getMainHabit(),
            params.getMainCardStudy()
        ));
    }


    public void handleSetUpWeek(List<String> stringProperties){
        if(stringProperties.size() < 11){
            throw new IllegalArgumentException("A lista de propriedades deve conter pelo menos 11 elementos.");
        }


        WeekSetupParameters params = new WeekSetupParameters.Builder()
                .withPlanName(stringProperties.get(0))
                .withObjectiveTitle(stringProperties.get(1))
                .withObjectiveDescription(stringProperties.get(2))
                .withMaterialTopic( stringProperties.get(3))
                .withMaterialFormat( stringProperties.get(4))
                .withGoal( stringProperties.get(5))
                .withReminderTitle( stringProperties.get(6))
                .withReminderDescription( stringProperties.get(7))
                .withMainTaskTitle( stringProperties.get(8))
                .withMainHabit( stringProperties.get(9))
                .withMainCardStudy( stringProperties.get(10))
                .build();

        setUpWeek(params);
    }


    public void addRegistry(Registry registry){
        registryList.add(registry);
    }
    public void removeRegistry(Registry registry){
        registryList.remove(registry);
    }
    public List<Registry> getRegistryList(){
        return registryList;
    }

    public List<String> searchInRegistries(String text){
        List<String> response = new ArrayList<>();
        for(Registry registry : registryList){
            String mix = (registry.getName() != null ? registry.getName() : "");
            if (mix.toLowerCase().contains(text.toLowerCase())){
                response.add(registry.getName());
            }
        }
        return response;
    }

}
