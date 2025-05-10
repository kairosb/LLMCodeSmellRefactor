package org.example.studymaterial;

import java.util.List;

public class AudioReference extends Reference {

    public record AudioMetadata(
    String title,
    String description, 
    String link,
    String accessRights,
    String license,
    String language
    ) {} 

    public record AudioStats(
    int rating,
    int viewCount,
    int shareCount
    ) {}
    public enum AudioQuality {
        LOW, MEDIUM, HIGH, VERY_HIGH;
    }
    private AudioQuality audioQuality;

    public AudioReference(AudioQuality quality){
        this.audioQuality = quality;
    }

    public AudioQuality getAudioQuality() {
        return audioQuality;
    }

    public static AudioQuality audioQualityAdapter(String quality){
        return switch (quality.toLowerCase()) {
            case "low" -> AudioQuality.LOW;
            case "medium" -> AudioQuality.MEDIUM;
            case "high" -> AudioQuality.HIGH;
            case "very_high" -> AudioQuality.VERY_HIGH;
            default -> null;
        };
    }

    public void setAudioQuality(AudioQuality audioQuality) {
        this.audioQuality = audioQuality;
    }

      public void editAudio(AudioQuality audioQuality, boolean isDownloadable, AudioMetadata metadata, AudioStats stats) {
        editBasic(metadata.title(), metadata.description(), metadata.link());
        this.setAccessRights(metadata.accessRights());
        this.setLicense(metadata.license());
        this.setAudioQuality(audioQuality);
        editVideoAttributes(stats.rating(), metadata.language(), stats.viewCount(), stats.shareCount(), isDownloadable);
    }

    public void editAudioAdapter(List<String> properties, List<Integer> intProperties, AudioQuality audioQuality, boolean isDownloadable) {
        AudioMetadata metadata = new AudioMetadata(
            properties.get(0),  // title
            properties.get(1),  // description
            properties.get(2),  // link
            properties.get(3),  // accessRights
            properties.get(4),  // license
            properties.get(5)   // language
        );
        
        AudioStats stats = new AudioStats(
            intProperties.get(0),  // rating
            intProperties.get(1),  // viewCount
            intProperties.get(2)   // shareCount
        );
        
        this.editAudio(audioQuality, isDownloadable, metadata, stats);
    }


     private void editVideoAttributes(int rating, String language, int viewCount, int shareCount,boolean isDownloadable){
         this.setRating(rating);
         this.setShareCount(shareCount);
         this.setViewCount(viewCount);
         this.setDownloadable(isDownloadable);
         this.setLanguage(language);
     }

     public void editBasic(String title, String description, String link){
         this.setTitle(title);
         this.setDescription(description);
         this.setLink(link);
     }
}