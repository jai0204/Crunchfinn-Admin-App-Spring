package com.crunchfinn.admin.disbursement.service;

import com.crunchfinn.admin.application.entity.ApplicationDetails;
import com.crunchfinn.admin.application.enums.ApplicationSource;
import com.crunchfinn.admin.application.repository.ApplicationDetailsRepository;
import com.crunchfinn.admin.bankpartner.entity.BankPartnerDetails;
import com.crunchfinn.admin.bankpartner.repository.BankPartnerDetailsRepository;
import com.crunchfinn.admin.disbursement.dto.DisbursementDetailsResponse;
import com.crunchfinn.admin.disbursement.dto.DisbursementListResponse;
import com.crunchfinn.admin.disbursement.dto.UpdateDisbursementRequest;
import com.crunchfinn.admin.disbursement.entity.DisbursementDetails;
import com.crunchfinn.admin.disbursement.entity.DisbursementTranche;
import com.crunchfinn.admin.disbursement.repository.DisbursementRepository;
import com.crunchfinn.admin.disbursement.repository.DisbursementTrancheRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DisbursementServiceImpl implements DisbursementService {
    private final DisbursementRepository disbursementRepository;
    private final DisbursementTrancheRepository trancheRepository;
    private final ApplicationDetailsRepository applicationRepository;
    private final BankPartnerDetailsRepository bankPartnerRepository;

    public DisbursementServiceImpl(DisbursementRepository disbursementRepository, DisbursementTrancheRepository trancheRepository,
                                   ApplicationDetailsRepository applicationRepository,
                                   BankPartnerDetailsRepository bankPartnerRepository) {
        this.disbursementRepository = disbursementRepository;
        this.trancheRepository = trancheRepository;
        this.applicationRepository = applicationRepository;
        this.bankPartnerRepository = bankPartnerRepository;
    }

    // =========================================================
    // CREATE (when status becomes DISBURSED or update bank partner)
    // =========================================================

    @Transactional
    public void createIfNotExists(Long applicationId, Long bankPartnerId) {

        boolean exists = disbursementRepository.existsByApplication_Id(applicationId);

        ApplicationDetails application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("Application not found"));

        BankPartnerDetails partner = bankPartnerRepository.findById(bankPartnerId)
                .orElseThrow(() -> new IllegalArgumentException("Bank Partner not found"));

        DisbursementDetails disbursement = null;
        if (exists) {
            disbursement = getByApplicationId(applicationId).get();
        } else {
            disbursement = new DisbursementDetails();
        }

        disbursement.setApplication(application);
        disbursement.setBankPartner(partner);

        disbursementRepository.save(disbursement);
    }

    // =========================================================
    // DELETE (when status is no longer DISBURSED)
    // =========================================================

    @Transactional
    public void remove(Long applicationId) {
        disbursementRepository.deleteByApplicationId(applicationId);
        disbursementRepository.flush(); // still recommended
    }

    // =========================================================
    // FETCH (for UI)
    // =========================================================

    public Optional<DisbursementDetails> getByApplicationId(Long applicationId) {
        return disbursementRepository.findByApplication_Id(applicationId);
    }

    @Override
    public List<DisbursementListResponse> getAllDisbursements() {
        return disbursementRepository.findAllForListView();
    }

    @Override
    public List<DisbursementListResponse> searchDisbursements(String search, String source, String disbursementMonth) {
        Integer month = null;
        Integer year = null;

        // Convert yyyy-MM → month & year
        if (disbursementMonth != null && !disbursementMonth.isBlank()) {
            YearMonth ym = YearMonth.parse(disbursementMonth);
            month = ym.getMonthValue();
            year = ym.getYear();
        }

        ApplicationSource sourceEnum = null;
        if (source != null && !source.isBlank()) {
            sourceEnum = ApplicationSource.valueOf(source);
        }

        List<DisbursementListResponse> list =
                disbursementRepository.searchDisbursements(search, sourceEnum, month, year);

        return list;
    }

    @Transactional
    public void updateTranches(UpdateDisbursementRequest request) {

        DisbursementDetails disbursement = disbursementRepository
                .findById(request.getDisbursementId())
                .orElseThrow(() -> new IllegalArgumentException("Not found"));

        List<UpdateDisbursementRequest.TrancheForm> incoming =
                request.getTranches() != null ? request.getTranches() : new ArrayList<>();

        // Existing tranches from DB
        Map<Long, DisbursementTranche> existingMap =
                disbursement.getTranches().stream()
                        .collect(Collectors.toMap(DisbursementTranche::getId, t -> t));

        List<DisbursementTranche> updatedList = new ArrayList<>();

        for (UpdateDisbursementRequest.TrancheForm t : incoming) {

            if (t.getId() != null && existingMap.containsKey(t.getId())) {
                // UPDATE
                DisbursementTranche existing = existingMap.get(t.getId());
                existing.setAmount(t.getAmount());
                existing.setDisbursementDate(t.getDisbursementDate());

                updatedList.add(existing);

                // mark as processed
                existingMap.remove(t.getId());

            } else if (t.getAmount() != null && t.getDisbursementDate() != null) {
                // CREATE
                DisbursementTranche newTranche = new DisbursementTranche();
                newTranche.setAmount(t.getAmount());
                newTranche.setDisbursementDate(t.getDisbursementDate());

                disbursement.addTranche(newTranche);
                updatedList.add(newTranche);
            }
        }

        // DELETE remaining (not in request)
        for (DisbursementTranche toDelete : existingMap.values()) {
            disbursement.removeTranche(toDelete);
        }

        disbursementRepository.save(disbursement);
    }

    public DisbursementDetailsResponse getDetails(Long id) {

        DisbursementDetails d = disbursementRepository.findWithTranches(id)
                .orElseThrow(() -> new IllegalArgumentException("Disbursement not found"));

        List<DisbursementDetailsResponse.TrancheResponse> tranches =
                d.getTranches().stream()
                        .map(t -> new DisbursementDetailsResponse.TrancheResponse(
                                t.getId(),
                                t.getAmount(),
                                t.getDisbursementDate()
                        ))
                        .toList();

        return new DisbursementDetailsResponse(d.getId(), tranches);
    }
}
