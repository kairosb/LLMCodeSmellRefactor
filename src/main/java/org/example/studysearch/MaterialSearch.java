package org.example.studysearch;

import org.example.studyregistry.StudyMaterial;
import java.util.List;

// MaterialSearch.java
public class MaterialSearch implements Search<String> {
    private SearchLog searchLog = new SearchLog("Material Search");

    public MaterialSearch() {}

    @Override
    public List<String> search(String text) {
        List<String> materialResults = StudyMaterial.getStudyMaterial().searchInMaterials(text);
        return searchLog.handleSearchAndLog(materialResults, text);
    }

    public SearchLog getSearchLog() {
        return searchLog;
    }
}