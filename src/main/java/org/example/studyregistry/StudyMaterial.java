package org.example.studyregistry;

import org.example.studymaterial.AudioReference;
import org.example.studymaterial.Reference;
import org.example.studymaterial.TextReference;
import org.example.studymaterial.VideoReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudyMaterial {
    private List<Reference> references;
    private static StudyMaterial studyMaterial;
    private Map<String, Integer> referenceCount;

    private StudyMaterial() {
        references = new ArrayList<Reference>();
    }

    public static StudyMaterial getStudyMaterial() {
        if (studyMaterial == null) {
            studyMaterial = new StudyMaterial();
        }
        return studyMaterial;
    }

    public void addReference(Reference ref) {
        references.add(ref);
    }

    List<Reference> getReferences() {
        return references;
    }

    public List<Reference> getReferencesByType(Reference type) {
        List<Reference> response = new ArrayList<>();
        for (Reference reference : references) {
            if (reference.getClass() == type.getClass()) {
                response.add(reference);
            }
        }
        return response;
    }

    public void setReferenceCount(Map<String, Integer> referenceCount) {
        this.referenceCount = referenceCount;
    }

    public List<String> searchInMaterials(String text) {
        List<String> response = new ArrayList<>();
        for (Reference reference : references) {
            String mix = (reference.getTitle() != null ? reference.getTitle() : "") 
                      + (reference.getDescription() != null ? reference.getDescription() : "");
            if (mix.toLowerCase().contains(text.toLowerCase())) {
                response.add(reference.getTitle());
            }
        }
        return response;
    }

    private void countAudioReferences(Reference reference, Map<String, Integer> response) {
        if (reference.getClass() == AudioReference.class) {
            Integer audioCount = response.get("Audio References");
            response.put("Audio References", audioCount + 1);
        }
    }

    private void countVideoReferences(Reference reference, Map<String, Integer> response) {
        if (reference.getClass() == VideoReference.class && ((VideoReference) reference).handleStreamAvailability()) {
            Integer videoCount = response.get("Video References");
            response.put("Video References", videoCount + 1);
        }
    }

    private void countTextReferences(Reference reference, Map<String, Integer> response) {
        if (reference.getClass() == TextReference.class && ((TextReference) reference).handleTextAccess()) {
            Integer textCount = response.get("Text References");
            response.put("Text References", textCount + 1);
        }
    }

    public Map<String, Integer> getReferenceCountMap() {
        Map<String, Integer> response = new HashMap<>();
        response.put("Audio References", 0);
        response.put("Video References", 0);
        response.put("Text References", 0);

        for (Reference reference : references) {
            countAudioReferences(reference, response);
            countVideoReferences(reference, response);
            countTextReferences(reference, response);
        }
        
        setReferenceCount(response);
        return response;
    }
}