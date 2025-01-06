
// AccessControl.java
package org.example.studymaterial;

public class AccessControl {
    private String accessRights;
    private String license;
    private boolean isDownloadable;

    public void updateAccessRights(String accessRights) {
        if (accessRights == null || accessRights.trim().isEmpty()) {
            throw new IllegalArgumentException("Access rights cannot be empty");
        }
        this.accessRights = accessRights;
        this.isDownloadable = "public".equalsIgnoreCase(accessRights);
    }

    public void setLicense(String license) {
        if (license == null || license.trim().isEmpty()) {
            throw new IllegalArgumentException("License cannot be empty");
        }
        this.license = license;
    }

    public boolean isDownloadable() {
        return isDownloadable;
    }

    public void setDownloadable(boolean downloadable) {
        isDownloadable = downloadable;
    }
}