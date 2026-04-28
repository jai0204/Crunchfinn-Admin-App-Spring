package com.crunchfinn.admin.application.service;

import com.crunchfinn.admin.application.dto.*;
import com.crunchfinn.admin.application.enums.ApplicationStatus;

import java.util.List;

public interface ApplicationService {

    ApplicationResponse createApplication(CreateApplicationRequest request);
    ApplicationResponse updateApplication(String applicationId, UpdateApplicationRequest request);
    ApplicationResponse getApplication(String applicationId);
    List<ApplicationResponse> searchApplications(String name, String status, String source);
    void updateAdminDetails(AdminUpdateRequest request);
    void deleteApplication(String applicationId);
    List<ApplicationStatus> getDisabledStatuses();
    ApplicationResponse getApplicationWithPartners(String applicationId);
    void assignPartner(String applicationId, AssignBankPartnerRequest request);
    void updateAll(Long id, UpdateApplicationBankPartnersRequest request);
}