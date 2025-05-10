package org.example.studysearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchLog {
    private final List<String> searchHistory;
    private final Map<String, Integer> searchCount;
    private boolean isLocked;
    private int numUsages;
    private final String logName;

    public SearchLog(String logName) {
        if (logName == null || logName.trim().isEmpty()) {
            throw new IllegalArgumentException("Log name cannot be null or empty");
        }
        this.searchHistory = new ArrayList<>();
        this.searchCount = new HashMap<>();
        this.logName = logName;
        this.numUsages = 0;
        this.isLocked = false;
    }

    // Método mantido para compatibilidade com os testes
    public void addSearchHistory(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            throw new IllegalArgumentException("Search term cannot be null or empty");
        }
        searchHistory.add(searchTerm);
    }

    public void addSearch(String searchTerm) {
        if (isLocked) {
            throw new IllegalStateException("Cannot add search when log is locked");
        }
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            throw new IllegalArgumentException("Search term cannot be null or empty");
        }

        searchHistory.add(searchTerm);
        searchCount.merge(searchTerm, 1, Integer::sum);
        numUsages++;
    }

    public List<String> getSearchHistory() {
        return Collections.unmodifiableList(searchHistory);
    }

    public int getSearchTermCount(String term) {
        return searchCount.getOrDefault(term, 0);
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public int getNumUsages() {
        return numUsages;
    }

    // Método mantido para compatibilidade com o código existente
    public void setNumUsages(int numUsages) {
        this.numUsages = numUsages;
    }

    public String getLogName() {
        return logName;
    }

    public void logSearch(String searchTerm) {
        addSearchHistory(searchTerm);
        setNumUsages(getNumUsages() + 1);
    }

    public String logSearchAndGetMessage(String searchTerm) {
        if (isLocked) {
            throw new IllegalStateException("Cannot add search when log is locked");
        }
        addSearch(searchTerm);
        return "\nLogged in: " + this.logName;
    }

    // SearchLog.java
// Adicionar novo método
    public List<String> handleSearchAndLog(List<String> searchResults, String searchTerm) {
        if (isLocked) {
            throw new IllegalStateException("Cannot add search when log is locked");
        }

        List<String> results = new ArrayList<>(searchResults);
        addSearch(searchTerm);
        results.add("\nLogged in: " + this.logName);
        return results;
    }
}