package com.crunchfinn.admin.bankpartner.dto;

import com.crunchfinn.admin.common.enums.Gender;

public class CreateBankPartnerRequest {

    // relation (we pass bankId from UI)
    private Long bankId;

    // identifiers
    private String bankPartnerId;

    // location
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String stateCode;
    private String pincode;
    private String gstin;

    // handler
    private String handlerName;
    private Gender handlerGender;
    private String handlerPhoneNumber;

    // email
    private String emailTo;
    private String emailCc;

    // getters & setters

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getBankPartnerId() {
        return bankPartnerId;
    }

    public void setBankPartnerId(String bankPartnerId) {
        this.bankPartnerId = bankPartnerId;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getGstin() {
        return gstin;
    }

    public void setGstin(String gstin) {
        this.gstin = gstin;
    }

    public String getHandlerName() {
        return handlerName;
    }

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }

    public Gender getHandlerGender() {
        return handlerGender;
    }

    public void setHandlerGender(Gender handlerGender) {
        this.handlerGender = handlerGender;
    }

    public String getHandlerPhoneNumber() {
        return handlerPhoneNumber;
    }

    public void setHandlerPhoneNumber(String handlerPhoneNumber) {
        this.handlerPhoneNumber = handlerPhoneNumber;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getEmailCc() {
        return emailCc;
    }

    public void setEmailCc(String emailCc) {
        this.emailCc = emailCc;
    }
}