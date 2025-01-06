
// Reference.java
package org.example.studymaterial;

public abstract class Reference {
    private String title;
    private String description;
    private String link;
    private String language;
    private final ReferenceMetrics metrics;
    private final AccessControl accessControl;

    protected Reference(String title, String link, String language) {
        setTitle(title);
        setLink(link);
        setLanguage(language);
        this.metrics = new ReferenceMetrics();
        this.accessControl = new AccessControl();
    }

    protected Reference() {
        this.metrics = new ReferenceMetrics();
        this.accessControl = new AccessControl();
    }

    public void view() {
        metrics.incrementViews();
    }

    public void download() {
        if (!accessControl.isDownloadable()) {
            throw new IllegalStateException("This reference is not downloadable");
        }
        metrics.incrementDownloads();
    }

    public void share() {
        metrics.incrementShares();
    }

    public void rate(int score) {
        metrics.setRating(score);
    }

    public boolean isPopular() {
        return metrics.isPopular();
    }

    // Getters e setters essenciais
    public String getTitle() {
        return title;
    }

    protected void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        this.title = title;
    }

    protected void setDescription(String description) {
        if (description != null && !description.trim().isEmpty()) {
            this.description = description;
        }
    }

    protected void setLink(String link) {
        if (link == null || link.trim().isEmpty()) {
            throw new IllegalArgumentException("Link cannot be empty");
        }
        this.link = link;
    }

    protected void setLanguage(String language) {
        if (language == null || language.trim().isEmpty()) {
            throw new IllegalArgumentException("Language cannot be empty");
        }
        this.language = language;
    }

    // Métodos delegados para AccessControl
    protected void setAccessRights(String accessRights) {
        accessControl.updateAccessRights(accessRights);
    }

    protected void setDownloadable(boolean downloadable) {
        accessControl.setDownloadable(downloadable);
    }

    protected boolean getIsDownloadable() {
        return accessControl.isDownloadable();
    }
}