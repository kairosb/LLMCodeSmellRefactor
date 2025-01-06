package org.example.studysearch;

import java.util.*;

public class SearchLog {
    private final List<String> searchHistory;
    private final Map<String, Integer> searchCount;
    private boolean isLocked;
    private Integer searchUsageCount;
    private final String logName;

    public SearchLog(String logName) {
        searchHistory = new ArrayList<>();
        searchCount = new HashMap<>();
        this.logName = logName;
        searchUsageCount = 0;
        isLocked = false;
    }

    public void recordSearch(String searchTerm) {
        if (!isLocked) {
            searchHistory.add(searchTerm);
            updateSearchCount(searchTerm);
            incrementUsage();
        }
    }

    private void updateSearchCount(String searchTerm) {
        searchCount.merge(searchTerm, 1, Integer::sum);
    }

    private void incrementUsage() {
        searchUsageCount++;
    }

    public List<String> getSearchHistory() {
        return Collections.unmodifiableList(searchHistory);
    }

    public int getSearchFrequency(String term) {
        return searchCount.getOrDefault(term, 0);
    }

    public void setLockStatus(boolean locked) {
        this.isLocked = locked;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public int getUsageCount() {
        return searchUsageCount;
    }

    public String getLogIdentifier() {
        return logName;
    }
}
