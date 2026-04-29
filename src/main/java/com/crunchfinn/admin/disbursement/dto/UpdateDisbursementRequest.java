package com.crunchfinn.admin.disbursement.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class UpdateDisbursementRequest {

    private Long disbursementId;
    private List<TrancheForm> tranches;

    public static class TrancheForm {
        private Long id; // null for new
        private BigDecimal amount;
        private LocalDate disbursementDate;

        // getters/setters

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
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
    }

    // getters/setters

    public Long getDisbursementId() {
        return disbursementId;
    }

    public void setDisbursementId(Long disbursementId) {
        this.disbursementId = disbursementId;
    }

    public List<TrancheForm> getTranches() {
        return tranches;
    }

    public void setTranches(List<TrancheForm> tranches) {
        this.tranches = tranches;
    }
}