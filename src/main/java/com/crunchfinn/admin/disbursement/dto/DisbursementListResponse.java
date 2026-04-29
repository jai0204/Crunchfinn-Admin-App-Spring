package com.crunchfinn.admin.disbursement.dto;

import com.crunchfinn.admin.application.enums.ApplicationSource;

import java.math.BigDecimal;

public class DisbursementListResponse {

    // Application details
    private Long applicationId;
    private String applicationNumber;
    private String name;
    private BigDecimal loanAmount;
    private String targetCountry;
    private ApplicationSource source;

    // Bank Partner details
    private String bankName;
    private String state;
    private String city;
    private String handlerName;

    // Disbursement
    private Long disbursementId;
    private Long trancheCount;

    // ===== Constructor =====

    public DisbursementListResponse(Long applicationId,
                                    String applicationNumber,
                                    String name,
                                    BigDecimal loanAmount,
                                    String targetCountry,
                                    ApplicationSource source,
                                    String bankName,
                                    String state,
                                    String city,
                                    String handlerName,
                                    Long disbursementId,
                                    Long trancheCount) {
        this.applicationId = applicationId;
        this.applicationNumber = applicationNumber;
        this.name = name;
        this.loanAmount = loanAmount;
        this.targetCountry = targetCountry;
        this.source = source;
        this.bankName = bankName;
        this.state = state;
        this.city = city;
        this.handlerName = handlerName;
        this.disbursementId = disbursementId;
        this.trancheCount = trancheCount;
    }

    // getters

    public Long getApplicationId() {
        return applicationId;
    }

    public String getApplicationNumber() {
        return applicationNumber;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public String getTargetCountry() {
        return targetCountry;
    }

    public ApplicationSource getSource() {
        return source;
    }

    public String getBankName() {
        return bankName;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getHandlerName() {
        return handlerName;
    }

    public Long getDisbursementId() {
        return disbursementId;
    }

    public Long getTrancheCount() {
        return trancheCount;
    }
}