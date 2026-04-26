package com.crunchfinn.admin.bankpartner.dto;

import java.util.List;

public class BankWithPartnersDTO {

    private Long bankId;
    private String bankName;

    private List<BankPartnerDropdownResponse> partners;

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public List<BankPartnerDropdownResponse> getPartners() {
        return partners;
    }

    public void setPartners(List<BankPartnerDropdownResponse> partners) {
        this.partners = partners;
    }
}