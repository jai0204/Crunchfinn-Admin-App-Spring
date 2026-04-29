package com.crunchfinn.admin.disbursement.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "disbursement_tranche")
public class DisbursementTranche {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Parent relation
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "disbursement_id", nullable = false)
    private DisbursementDetails disbursement;

    // tranche data
    @Column(name = "disbursement_amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(name = "disbursement_date", nullable = false)
    private LocalDate disbursementDate;

    // timestamp
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

    // ===== Getters & Setters =====

    public Long getId() {
        return id;
    }

    public DisbursementDetails getDisbursement() {
        return disbursement;
    }

    public void setDisbursement(DisbursementDetails disbursement) {
        this.disbursement = disbursement;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDisbursementDate() {
        return disbursementDate;
    }

    public void setDisbursementDate(LocalDate disbursementDate) {
        this.disbursementDate = disbursementDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}