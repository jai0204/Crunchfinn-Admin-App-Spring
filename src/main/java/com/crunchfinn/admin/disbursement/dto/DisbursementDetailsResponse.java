package com.crunchfinn.admin.disbursement.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class DisbursementDetailsResponse {

    private Long id;
    private List<TrancheResponse> tranches;

    public static class TrancheResponse {
        private Long id;
        private BigDecimal amount;
        private LocalDate disbursementDate;

        public TrancheResponse(Long id, BigDecimal amount, LocalDate disbursementDate) {
            this.id = id;
            this.amount = amount;
            this.disbursementDate = disbursementDate;
        }

        public Long getId() { return id; }
        public BigDecimal getAmount() { return amount; }
        public LocalDate getDisbursementDate() { return disbursementDate; }
    }

    public DisbursementDetailsResponse(Long id, List<TrancheResponse> tranches) {
        this.id = id;
        this.tranches = tranches;
    }

    public Long getId() { return id; }
    public List<TrancheResponse> getTranches() { return tranches; }
}