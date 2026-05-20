package com.crunchfinn.admin.disbursement.service;

import com.crunchfinn.admin.disbursement.dto.DisbursementDetailsResponse;
import com.crunchfinn.admin.disbursement.dto.DisbursementListResponse;
import com.crunchfinn.admin.disbursement.dto.UpdateDisbursementRequest;
import com.crunchfinn.admin.disbursement.entity.DisbursementDetails;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface DisbursementService {
    void createIfNotExists(Long applicationId, Long bankPartnerId);
    void remove(Long applicationId);
    Optional<DisbursementDetails> getByApplicationId(Long applicationId);
    List<DisbursementListResponse> getAllDisbursements();
    Page<DisbursementListResponse> getAllDisbursements(int page, int size);
    List<DisbursementListResponse> searchDisbursements(String search, String source, String disbursementMonth);
    Page<DisbursementListResponse> searchDisbursements(String search, String source, String disbursementMonth, int page, int size);
    void updateTranches(UpdateDisbursementRequest request);
    DisbursementDetailsResponse getDetails(Long id);
}