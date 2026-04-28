package com.crunchfinn.admin.application.dto;

import com.crunchfinn.admin.application.enums.ApplicationPartnerStatus;

public class ApplicationBankPartnerResponse {

    private Long id;

    private Long bankPartnerId;

    private String bankName;
    private String handlerName;
    private String city;
    private String handlerPhoneNumber;

    private ApplicationPartnerStatus status;
    private String notes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBankPartnerId() {
        return bankPartnerId;
    }

    public void setBankPartnerId(Long bankPartnerId) {
        this.bankPartnerId = bankPartnerId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getHandlerName() {
        return handlerName;
    }

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHandlerPhoneNumber() {
        return handlerPhoneNumber;
    }

    public void setHandlerPhoneNumber(String handlerPhoneNumber) {
        this.handlerPhoneNumber = handlerPhoneNumber;
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