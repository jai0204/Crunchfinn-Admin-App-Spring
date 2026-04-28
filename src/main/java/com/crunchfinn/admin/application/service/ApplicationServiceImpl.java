package com.crunchfinn.admin.application.service;

import com.crunchfinn.admin.application.dto.*;
import com.crunchfinn.admin.application.entity.ApplicationDetails;
import com.crunchfinn.admin.application.enums.ApplicationSource;
import com.crunchfinn.admin.application.enums.ApplicationStatus;
import com.crunchfinn.admin.application.mapper.ApplicationMapper;
import com.crunchfinn.admin.application.repository.ApplicationDetailsRepository;
import com.crunchfinn.admin.common.service.CountryService;
import com.crunchfinn.admin.common.service.StateCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationDetailsRepository repository;
    private final ApplicationMapper mapper;
    private final StateCityService stateCityService;
    private final CountryService countryService;

    private final String APP_PREFIX = "CF-APP-";

    @Autowired
    public ApplicationServiceImpl(ApplicationDetailsRepository repository, ApplicationMapper mapper, StateCityService stateCityService, CountryService countryService) {
        this.repository = repository;
        this.mapper = mapper;
        this.stateCityService = stateCityService;
        this.countryService = countryService;
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
}
