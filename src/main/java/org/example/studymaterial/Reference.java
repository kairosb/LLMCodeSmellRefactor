package org.example.studymaterial;

public abstract class Reference {
    private String title;
    private String description;
    private String link;
    private String accessRights;
    private String license;
    private boolean isDownloadable;
    private int rating;
    private String language;
    private int viewCount;
    private int downloadCount;
    private int shareCount;
    private static final int MAX_RATING = 5;
    private static final int MIN_RATING = 1;

    protected enum ReferenceEvent {
        ACCESS, DOWNLOAD, SHARE
    }

    protected Reference() {
        this.viewCount = 0;
        this.downloadCount = 0;
        this.shareCount = 0;
        this.rating = 0;
    }

    protected Reference(String title, String description, String link) {
        this();
        setTitle(title);
        setDescription(description);
        setLink(link);
    }

    // Métodos de negócio significativos
    public void interact() {
        incrementViewCount();
        notify(ReferenceEvent.ACCESS);
    }

    public void share() {
        if (isAccessible()) {
            incrementShareCount();
            notify(ReferenceEvent.SHARE);
        }
    }

    public void download() {
        if (isDownloadable && isAccessible()) {
            incrementDownloadCount();
            notify(ReferenceEvent.DOWNLOAD);
        }
    }

    protected void notify(ReferenceEvent event) {
        System.out.println(String.format("%s: %s event on %s",
                getClass().getSimpleName(), event.name(), getTitle()));
    }

    public boolean isPopular() {
        return viewCount > 1000 || downloadCount > 100 || shareCount > 50;
    }

    public double getEngagementScore() {
        return (viewCount * 0.4) + (downloadCount * 0.4) + (shareCount * 0.2);
    }

    private void incrementViewCount() {
        this.viewCount++;
    }

    private void incrementDownloadCount() {
        this.downloadCount++;
    }

    private void incrementShareCount() {
        this.shareCount++;
    }

    public void updateRating(int newRating) {
        if (newRating < MIN_RATING || newRating > MAX_RATING) {
            throw new IllegalArgumentException("Rating deve estar entre " + MIN_RATING + " e " + MAX_RATING);
        }
        this.rating = newRating;
    }

    public boolean isAccessible() {
        return "public".equalsIgnoreCase(accessRights);
    }
    // Getters e Setters necessários para compatibilidade
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Título não pode ser vazio");
        }
        this.title = title.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description != null) {
            this.description = description.trim();
        }
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        if (link != null && !link.trim().isEmpty()) {
            this.link = link.trim();
        }
    }

    public String getAccessRights() {
        return accessRights;
    }

    public void setAccessRights(String accessRights) {
        this.accessRights = accessRights;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public boolean getIsDownloadable() {
        return isDownloadable;
    }

    public void setDownloadable(boolean downloadable) {
        isDownloadable = downloadable;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    public int getShareCount() {
        return shareCount;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }
}