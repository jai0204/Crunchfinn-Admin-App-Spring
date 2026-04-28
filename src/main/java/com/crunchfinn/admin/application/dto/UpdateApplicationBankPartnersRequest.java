package com.crunchfinn.admin.application.dto;

import com.crunchfinn.admin.application.enums.ApplicationPartnerStatus;

import java.util.List;

public class UpdateApplicationBankPartnersRequest {

    private Long applicationId;
    private List<PartnerUpdate> partners;

    public List<PartnerUpdate> getPartners() {
        return partners;
    }

    public void setPartners(List<PartnerUpdate> partners) {
        this.partners = partners;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public static class PartnerUpdate {
        private Long id;
        private ApplicationPartnerStatus status;
        private String notes;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public ApplicationPartnerStatus getStatus() {
            return status;
        }

        public void setStatus(ApplicationPartnerStatus status) {
            this.status = status;
        }

        public String getNotes() {
            return notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }
    }
}