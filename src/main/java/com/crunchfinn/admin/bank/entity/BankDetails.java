package com.crunchfinn.admin.bank.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "bank_details")
public class BankDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "bank_code", unique = true, length = 20)
    private String bankCode;

    // basic info
    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

    @Column(name = "official_name", length = 200)
    private String officialName;

    @Column(name = "partnership_date")
    private LocalDate partnershipDate;

    // contract details
    @Column(name = "partner_code", length = 100)
    private String partnerCode;

    @Column(name = "contract_start_date")
    private LocalDate contractStartDate;

    @Column(name = "contract_end_date")
    private LocalDate contractEndDate;

    @Column(name = "commission", precision = 5, scale = 2)
    private BigDecimal commission;

    // roi details
    @Column(name = "roi_min", precision = 5, scale = 2)
    private BigDecimal roiMin;

    @Column(name = "roi_max", precision = 5, scale = 2)
    private BigDecimal roiMax;

    // timestamps
    @Column(name = "created_at", updatable = false, insertable = false)
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

    @Override
    public String toString() {
        return "BankDetails{" +
                "id=" + id +
                ", bankCode='" + bankCode + '\'' +
                ", name='" + name + '\'' +
                ", officialName='" + officialName + '\'' +
                ", partnershipDate=" + partnershipDate +
                ", partnerCode='" + partnerCode + '\'' +
                ", contractStartDate=" + contractStartDate +
                ", contractEndDate=" + contractEndDate +
                ", commission=" + commission +
                ", roiMin=" + roiMin +
                ", roiMax=" + roiMax +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
