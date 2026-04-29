package com.crunchfinn.admin.disbursement.entity;

import com.crunchfinn.admin.application.entity.ApplicationDetails;
import com.crunchfinn.admin.bankpartner.entity.BankPartnerDetails;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "disbursement_details",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "application_id")
        }
)
public class DisbursementDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Application (1:1 effectively due to unique constraint)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false)
    private ApplicationDetails application;

    // Bank Partner
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_partner_id", nullable = false)
    private BankPartnerDetails bankPartner;

    @OneToMany(
            mappedBy = "disbursement",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @OrderBy("disbursementDate ASC, id ASC")
    private List<DisbursementTranche> tranches = new ArrayList<>();

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public List<DisbursementTranche> getTranches() {
        return tranches;
    }

    public void setTranches(List<DisbursementTranche> tranches) {
        this.tranches = tranches;
    }

    public void addTranche(DisbursementTranche tranche) {
        tranches.add(tranche);
        tranche.setDisbursement(this);
    }

    public void removeTranche(DisbursementTranche tranche) {
        tranches.remove(tranche);
        tranche.setDisbursement(null);
    }
}