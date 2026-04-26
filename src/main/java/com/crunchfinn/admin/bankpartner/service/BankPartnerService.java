package com.crunchfinn.admin.bankpartner.service;

import com.crunchfinn.admin.bankpartner.dto.BankPartnerResponse;
import com.crunchfinn.admin.bankpartner.dto.BankWithPartnersDTO;
import com.crunchfinn.admin.bankpartner.dto.CreateBankPartnerRequest;
import com.crunchfinn.admin.bankpartner.dto.UpdateBankPartnerRequest;

import java.util.List;

public interface BankPartnerService {
    BankPartnerResponse createPartner(CreateBankPartnerRequest request);
    void updatePartner(UpdateBankPartnerRequest request);
    BankPartnerResponse getById(Long id);
    BankPartnerResponse getByPartnerId(String partnerId);
    List<BankPartnerResponse> getAllPartners();
    List<BankPartnerResponse> getPartnersByBank(Long bankId);
    void deletePartner(Long id);
    List<BankWithPartnersDTO> getBanksWithPartners();
}
