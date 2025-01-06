// ReferenceMetrics.java
package org.example.studymaterial;

public class ReferenceMetrics {
    private int viewCount;
    private int downloadCount;
    private int shareCount;
    private int rating;

    public ReferenceMetrics() {
        this.viewCount = 0;
        this.downloadCount = 0;
        this.shareCount = 0;
        this.rating = 0;
    }

    public void incrementViews() {
        this.viewCount++;
    }

    public void incrementDownloads() {
        this.downloadCount++;
    }

    public void incrementShares() {
        this.shareCount++;
    }

    public void setRating(int score) {
        if (score < 1 || score > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
        this.rating = score;
    }

    public boolean isPopular() {
        return viewCount > 1000 || downloadCount > 100;
    }

    public int getRating() {
        return rating;
    }
}