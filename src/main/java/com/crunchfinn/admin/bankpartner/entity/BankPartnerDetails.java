package com.crunchfinn.admin.bankpartner.entity;

import com.crunchfinn.admin.bank.entity.BankDetails;
import com.crunchfinn.admin.common.enums.Gender;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bank_partner_details")
public class BankPartnerDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // RELATION (Many partners → one bank)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id", nullable = false)
    private BankDetails bank;

    // identifiers
    @Column(name = "bank_partner_id", unique = true, length = 30)
    private String bankPartnerId;

    // location details
    @Column(name = "address_line1", length = 200)
    private String addressLine1;

    @Column(name = "address_line2", length = 200)
    private String addressLine2;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "state", length = 50)
    private String state;

    @Column(name = "state_code", length = 2)
    private String stateCode;

    @Column(name = "pincode", length = 6)
    private String pincode;

    @Column(name = "gstin", length = 20)
    private String gstin;

    // handler details
    @Column(name = "handler_name", length = 100)
    private String handlerName;

    @Enumerated(EnumType.STRING)
    @Column(name = "handler_gender", length = 10)
    private Gender handlerGender;

    @Column(name = "handler_phone_number", length = 11)
    private String handlerPhoneNumber;

    // email
    @Column(name = "email_to", length = 100)
    private String emailTo;

    @Column(name = "email_cc", length = 300)
    private String emailCc;

    // timestamps
    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;

    /* Lifecycle hooks */
    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // getters & setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BankDetails getBank() {
        return bank;
    }

    public void setBank(BankDetails bank) {
        this.bank = bank;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "BankPartnerDetails{" +
                "id=" + id +
                ", bank=" + bank +
                ", bankPartnerId='" + bankPartnerId + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", stateCode='" + stateCode + '\'' +
                ", pincode='" + pincode + '\'' +
                ", gstin='" + gstin + '\'' +
                ", handlerName='" + handlerName + '\'' +
                ", handlerGender=" + handlerGender +
                ", handlerPhoneNumber='" + handlerPhoneNumber + '\'' +
                ", emailTo='" + emailTo + '\'' +
                ", emailCc='" + emailCc + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}