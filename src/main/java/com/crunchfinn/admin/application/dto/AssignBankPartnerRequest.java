package com.crunchfinn.admin.application.dto;

import com.crunchfinn.admin.application.enums.ApplicationPartnerStatus;

public class AssignBankPartnerRequest {

    private Long bankPartnerId;
    private ApplicationPartnerStatus status;
    private String notes;

    public Long getBankPartnerId() {
        return bankPartnerId;
    }

    public void setBankPartnerId(Long bankPartnerId) {
        this.bankPartnerId = bankPartnerId;
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