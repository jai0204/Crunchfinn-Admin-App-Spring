package com.crunchfinn.admin.application.entity;

import com.crunchfinn.admin.application.enums.ApplicationPartnerStatus;
import com.crunchfinn.admin.bankpartner.entity.BankPartnerDetails;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "application_bank_partner",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"application_id", "bank_partner_id"})
        }
)
public class ApplicationBankPartner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Application relation
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false)
    private ApplicationDetails application;

    // Bank Partner relation
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_partner_id", nullable = false)
    private BankPartnerDetails bankPartner;

    // Assignment-specific fields
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    private ApplicationPartnerStatus status = ApplicationPartnerStatus.ASSIGNED;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

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

    // ===== Getters & Setters =====

    public Long getId() {
        return id;
    }

    public ApplicationDetails getApplication() {
        return application;
    }

    public void setApplication(ApplicationDetails application) {
        this.application = application;
    }

    public BankPartnerDetails getBankPartner() {
        return bankPartner;
    }

    public void setBankPartner(BankPartnerDetails bankPartner) {
        this.bankPartner = bankPartner;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}