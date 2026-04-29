package com.crunchfinn.admin.disbursement.service;

import com.crunchfinn.admin.disbursement.dto.DisbursementDetailsResponse;
import com.crunchfinn.admin.disbursement.dto.DisbursementListResponse;
import com.crunchfinn.admin.disbursement.dto.UpdateDisbursementRequest;
import com.crunchfinn.admin.disbursement.entity.DisbursementDetails;

import java.util.List;
import java.util.Optional;

public interface DisbursementService {
    void createIfNotExists(Long applicationId, Long bankPartnerId);
    void remove(Long applicationId);
    Optional<DisbursementDetails> getByApplicationId(Long applicationId);
    List<DisbursementListResponse> getAllDisbursements();
    List<DisbursementListResponse> searchDisbursements(String search, String source, String disbursementMonth);
    void updateTranches(UpdateDisbursementRequest request);
    DisbursementDetailsResponse getDetails(Long id);
}