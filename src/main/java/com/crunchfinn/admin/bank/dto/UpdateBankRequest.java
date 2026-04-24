package com.crunchfinn.admin.bank.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class UpdateBankRequest {

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

    // getters & setters

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
}