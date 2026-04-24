package com.crunchfinn.admin.bank.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class BankResponse {

    private Long id;
    private String bankCode;

    private String name;
    private String officialName;
    private LocalDate partnershipDate;

    // contract details
    private String partnerCode;
    private LocalDate contractStartDate;
    private LocalDate contractEndDate;
    private BigDecimal commission;

    // roi
    private BigDecimal roiMin;
    private BigDecimal roiMax;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // getters & setters
    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOfficialName() {
        return officialName;
    }

    public void setOfficialName(String officialName) {
        this.officialName = officialName;
    }

    public LocalDate getPartnershipDate() {
        return partnershipDate;
    }

    public void setPartnershipDate(LocalDate partnershipDate) {
        this.partnershipDate = partnershipDate;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public LocalDate getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(LocalDate contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    public LocalDate getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(LocalDate contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public BigDecimal getRoiMin() {
        return roiMin;
    }

    public void setRoiMin(BigDecimal roiMin) {
        this.roiMin = roiMin;
    }

    public BigDecimal getRoiMax() {
        return roiMax;
    }

    public void setRoiMax(BigDecimal roiMax) {
        this.roiMax = roiMax;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}