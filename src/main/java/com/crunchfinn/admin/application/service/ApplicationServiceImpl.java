package com.crunchfinn.admin.application.service;

import com.crunchfinn.admin.application.dto.*;
import com.crunchfinn.admin.application.entity.ApplicationBankPartner;
import com.crunchfinn.admin.application.entity.ApplicationDetails;
import com.crunchfinn.admin.application.enums.ApplicationPartnerStatus;
import com.crunchfinn.admin.application.enums.ApplicationSource;
import com.crunchfinn.admin.application.enums.ApplicationStatus;
import com.crunchfinn.admin.application.mapper.ApplicationMapper;
import com.crunchfinn.admin.application.repository.ApplicationBankPartnerRepository;
import com.crunchfinn.admin.application.repository.ApplicationDetailsRepository;
import com.crunchfinn.admin.bankpartner.entity.BankPartnerDetails;
import com.crunchfinn.admin.bankpartner.repository.BankPartnerDetailsRepository;
import com.crunchfinn.admin.common.service.CountryService;
import com.crunchfinn.admin.common.service.StateCityService;
import com.crunchfinn.admin.disbursement.service.DisbursementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationDetailsRepository repository;
    private final BankPartnerDetailsRepository bankPartnerDetailsRepository;
    private final ApplicationBankPartnerRepository applicationBankPartnerRepository;
    private final ApplicationMapper mapper;
    private final StateCityService stateCityService;
    private final CountryService countryService;
    private final DisbursementService disbursementService;

    private final String APP_PREFIX = "CF-APP-";

    @Autowired
    public ApplicationServiceImpl(ApplicationDetailsRepository repository, BankPartnerDetailsRepository bankPartnerDetailsRepository, ApplicationBankPartnerRepository applicationBankPartnerRepository, ApplicationMapper mapper, StateCityService stateCityService, CountryService countryService, DisbursementService disbursementService) {
        this.repository = repository;
        this.bankPartnerDetailsRepository = bankPartnerDetailsRepository;
        this.applicationBankPartnerRepository = applicationBankPartnerRepository;
        this.mapper = mapper;
        this.stateCityService = stateCityService;
        this.countryService = countryService;
        this.disbursementService = disbursementService;
    }

    @Override
    @Transactional
    public ApplicationResponse createApplication(CreateApplicationRequest request) {
        ApplicationDetails application = mapper.toEntity(request);

        // save first to generate auto-increment id
        repository.save(application);

        // generate application id
        String applicationId = APP_PREFIX + String.format("%06d", application.getId());
        application.setApplicationId(applicationId);

        repository.save(application);

        return mapper.toResponse(application);
    }

    @Override
    @Transactional
    public ApplicationResponse updateApplication(String applicationId, UpdateApplicationRequest request) {
        ApplicationDetails application = repository.findByApplicationId(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        mapper.updateEntity(application, request);
        repository.save(application);

        return mapper.toResponse(application);
    }

    @Override
    public ApplicationResponse getApplication(String applicationId) {
        ApplicationDetails applicationDetails = repository.findByApplicationId(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found!"));

        return populateAdditionalFields(mapper.toResponse(applicationDetails));
    }

    @Override
    public ApplicationResponse getApplicationWithPartners(String applicationId) {
        ApplicationDetails applicationDetails = repository.findByApplicationIdWithPartners(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found!"));

        return populateAdditionalFields(mapper.toResponse(applicationDetails));
    }

    @Override
    public List<ApplicationResponse> searchApplications(String name, String status, String source) {

        ApplicationStatus statusEnum = null;
        ApplicationSource sourceEnum = null;

        if (status != null && !status.isBlank()) {
            statusEnum = ApplicationStatus.valueOf(status);
        }
        if (source != null && !source.isBlank()) {
            sourceEnum = ApplicationSource.valueOf(source);
        }

        List<ApplicationDetails> entities =
                repository.searchApplications(name, statusEnum, sourceEnum);

        return entities.stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public void updateAdminDetails(AdminUpdateRequest request) {
        ApplicationDetails entity =
                repository.findByApplicationId(request.getApplicationId())
                        .orElseThrow(() -> new RuntimeException("Application Not found"));

        entity.setStatus(request.getStatus());
        entity.setSource(request.getSource());
        entity.setAdminNotes(request.getAdminNotes());

        repository.save(entity);
    }

    @Override
    @Transactional
    public void deleteApplication(String applicationId) {

        if (!repository.existsByApplicationId(applicationId)) {
            throw new RuntimeException("Application not found");
        }

        repository.deleteByApplicationId(applicationId);
    }

    @Override
    public List<ApplicationStatus> getDisabledStatuses() {
        return List.of(ApplicationStatus.SENT_TO_BANK, ApplicationStatus.SANCTIONED,
                ApplicationStatus.DISBURSED, ApplicationStatus.REJECTED);
    }

    private ApplicationResponse populateAdditionalFields(ApplicationResponse response) {
        response.setStateName(stateCityService.getStateName(response.getState()));
        response.setTargetCountryName(countryService.getCountryName(response.getTargetCountry()));
        response.setCoapplicantStateName(stateCityService.getStateName(response.getCoapplicantState()));
        return response;
    }

    @Override
    public void assignPartner(String applicationId, AssignBankPartnerRequest request) {

        ApplicationDetails application = repository.findByApplicationId(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("Application not found"));

        BankPartnerDetails partner = bankPartnerDetailsRepository.findById(request.getBankPartnerId())
                .orElseThrow(() -> new IllegalArgumentException("Bank Partner not found"));

        // Prevent duplicate assignment
        boolean exists = applicationBankPartnerRepository
                .existsByApplicationIdAndBankPartnerId(application.getId(), request.getBankPartnerId());

        if (exists) {
            throw new IllegalArgumentException("Partner already assigned to this application");
        }

        // Check duplicate DISBURSED status
        List<ApplicationBankPartner> existing =
                applicationBankPartnerRepository.findAllByApplication_Id(application.getId());

        boolean hasDisbursed = existing.stream()
                .anyMatch(p -> p.getStatus() == ApplicationPartnerStatus.DISBURSED);
        if (request.getStatus() == ApplicationPartnerStatus.DISBURSED && hasDisbursed) {
            throw new IllegalArgumentException("Only one bank partner can be DISBURSED for an application");
        }

        ApplicationBankPartner abp = new ApplicationBankPartner();
        abp.setApplication(application);
        abp.setBankPartner(partner);
        abp.setStatus(request.getStatus() != null
                ? request.getStatus()
                : ApplicationPartnerStatus.ASSIGNED);
        abp.setNotes(request.getNotes());

        applicationBankPartnerRepository.save(abp);

        // IMPORTANT: recalculate best status
        updateApplicationBestStatus(application.getId(), existing);
    }

    public void updateAll(Long id, UpdateApplicationBankPartnersRequest request) {

        List<ApplicationBankPartner> existing =
                applicationBankPartnerRepository.findAllByApplication_Id(id);

        Map<Long, ApplicationBankPartner> map =
                existing.stream()
                        .collect(Collectors.toMap(ApplicationBankPartner::getId, e -> e));

        long disbursedCount = request.getPartners().stream()
                .filter(p -> p.getStatus() == ApplicationPartnerStatus.DISBURSED)
                .count();
        if (disbursedCount > 1) {
            throw new IllegalArgumentException("Only one bank partner can be DISBURSED for an application");
        }

        for (UpdateApplicationBankPartnersRequest.PartnerUpdate update : request.getPartners()) {

            ApplicationBankPartner abp = map.get(update.getId());

            if (abp == null) continue;

            if (update.getStatus() != null) {
                abp.setStatus(update.getStatus());
            }

            abp.setNotes(update.getNotes());
        }

        applicationBankPartnerRepository.saveAll(existing);

        // IMPORTANT: recalculate best status
        updateApplicationBestStatus(id, existing);
    }

    private void updateApplicationBestStatus(Long id,
                                             List<ApplicationBankPartner> partners) {

        ApplicationDetails app = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Application not found"));

        ApplicationStatus finalStatus = ApplicationStatus.SENT_TO_BANK;
        ApplicationBankPartner disbursedPartner = null;

        int total = partners.size();
        int rejectedCount = 0;
        int closedCount = 0;

        for (ApplicationBankPartner p : partners) {
            ApplicationPartnerStatus status = p.getStatus();

            if (status == ApplicationPartnerStatus.DISBURSED) {
                finalStatus = ApplicationStatus.DISBURSED;
                disbursedPartner = p;
                break;
            }
            if (status == ApplicationPartnerStatus.SANCTIONED) {
                finalStatus = ApplicationStatus.SANCTIONED;
            }
            if (status == ApplicationPartnerStatus.REJECTED) {
                rejectedCount++;
            }
            if (status == ApplicationPartnerStatus.CLOSED) {
                closedCount++;
            }
        }

        if (rejectedCount > 0 && rejectedCount == total) {
            finalStatus = ApplicationStatus.REJECTED;
        } else if (closedCount > 0 && closedCount == total) {
            finalStatus = ApplicationStatus.CLOSED;
        } else if (rejectedCount > 0 && closedCount > 0 && (rejectedCount + closedCount == total)) {
            finalStatus = ApplicationStatus.CLOSED;
        }

        app.setStatus(finalStatus);
        repository.save(app);

        if (finalStatus == ApplicationStatus.DISBURSED) {
            disbursementService.createIfNotExists(id, disbursedPartner.getBankPartner().getId());
        } else {
            disbursementService.remove(id);
        }
    }
}
