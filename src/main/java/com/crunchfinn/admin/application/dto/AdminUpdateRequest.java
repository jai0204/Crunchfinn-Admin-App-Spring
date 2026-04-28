package com.crunchfinn.admin.application.dto;

import com.crunchfinn.admin.application.enums.ApplicationSource;
import com.crunchfinn.admin.application.enums.ApplicationStatus;

public class AdminUpdateRequest {
    private String applicationId;

    private ApplicationStatus status;
    private ApplicationSource source;
    private String adminNotes;

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public ApplicationSource getSource() {
        return source;
    }

    public void setSource(ApplicationSource source) {
        this.source = source;
    }

    public String getAdminNotes() {
        return adminNotes;
    }

    public void setAdminNotes(String adminNotes) {
        this.adminNotes = adminNotes;
    }
}
