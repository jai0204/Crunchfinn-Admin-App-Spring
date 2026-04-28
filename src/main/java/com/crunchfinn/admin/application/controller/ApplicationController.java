package com.crunchfinn.admin.application.controller;

import com.crunchfinn.admin.application.dto.*;
import com.crunchfinn.admin.application.enums.*;
import com.crunchfinn.admin.application.service.ApplicationService;
import com.crunchfinn.admin.bankpartner.dto.BankWithPartnersDTO;
import com.crunchfinn.admin.bankpartner.service.BankPartnerService;
import com.crunchfinn.admin.common.enums.Gender;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/applications")
public class ApplicationController {
    private final ApplicationService applicationService;
    private final BankPartnerService bankPartnerService;

    @Autowired
    public ApplicationController(ApplicationService applicationService, BankPartnerService bankPartnerService) {
        this.applicationService = applicationService;
        this.bankPartnerService = bankPartnerService;
    }

    @GetMapping
    public String listApplications(@RequestParam(required = false) String name,
                                   @RequestParam(required = false) String status,
                                   @RequestParam(required = false) String source,
                                   HttpServletRequest request,
                                   Model model) {
        List<ApplicationResponse> applications = null;
        if (name != null || status != null || source != null) {
            applications = applicationService.searchApplications(name, status, source);
        }

        model.addAttribute("applications", applications);
        model.addAttribute("name", name);
        model.addAttribute("status", status);
        model.addAttribute("source", source);
        model.addAttribute("statusList", ApplicationStatus.values());
        model.addAttribute("sourceList", ApplicationSource.values());
        model.addAttribute("currentPath", request.getRequestURI());

        return "applications/list";
    }

    @GetMapping("/new")
    public String showCreateForm(HttpServletRequest request, Model model) {
        model.addAttribute("application", new CreateApplicationRequest());
        model.addAttribute("genderList", Gender.values());
        model.addAttribute("courseLevelList", CourseLevel.values());
        model.addAttribute("coapplicantNoeList", ApplicantNOE.values());
        model.addAttribute("collateralCategoryList", CollateralCategory.values());
        int currentYear = java.time.Year.now().getValue();
        model.addAttribute("currentYear", currentYear);
        model.addAttribute("currentPath", request.getRequestURI());

        return "applications/create";
    }

    // create application
    @PostMapping
    public String createApplication(@ModelAttribute CreateApplicationRequest request,
                                    RedirectAttributes redirectAttributes) {
        ApplicationResponse response = applicationService.createApplication(request);

        redirectAttributes.addFlashAttribute("success", "Application created successfully.");

        return "redirect:/applications/" + response.getApplicationId();
    }

    // view application
    @GetMapping("/{applicationId}")
    public String viewApplication(@PathVariable String applicationId,
                                  HttpServletRequest request,
                                  Model model) {
        ApplicationResponse response = applicationService.getApplicationWithPartners(applicationId);

        List<BankWithPartnersDTO> banksWithPartners =
                bankPartnerService.getBanksWithPartners();

        AdminUpdateRequest adminForm = new AdminUpdateRequest();

        adminForm.setApplicationId(response.getApplicationId());
        adminForm.setStatus(response.getStatus());
        adminForm.setSource(response.getSource());
        adminForm.setAdminNotes(response.getAdminNotes());

        model.addAttribute("app", response);
        model.addAttribute("banks", banksWithPartners);
        model.addAttribute("adminForm", adminForm);
        model.addAttribute("statusList", ApplicationStatus.values());
        model.addAttribute("sourceList", ApplicationSource.values());
        model.addAttribute("partnerStatusList", ApplicationPartnerStatus.values());
        model.addAttribute("disabledStatuses", applicationService.getDisabledStatuses());
        model.addAttribute("currentPath", request.getRequestURI());

        return "applications/view";
    }

    @PostMapping("/admin-update")
    public String updateAdminDetails(@ModelAttribute("adminForm") AdminUpdateRequest request,
                                     RedirectAttributes redirectAttributes) {
        applicationService.updateAdminDetails(request);

        redirectAttributes.addFlashAttribute("success", "Admin details updated successfully.");

        return "redirect:/applications/" + request.getApplicationId();
    }

    @GetMapping("/{applicationId}/edit")
    public String editPage(@PathVariable String applicationId,
                           HttpServletRequest request,
                           Model model) {
        ApplicationResponse response = applicationService.getApplication(applicationId);

        model.addAttribute("editForm", response);
        model.addAttribute("genderList", Gender.values());
        model.addAttribute("courseLevelList", CourseLevel.values());
        model.addAttribute("coapplicantNoeList", ApplicantNOE.values());
        model.addAttribute("collateralCategoryList", CollateralCategory.values());
        int currentYear = java.time.Year.now().getValue();
        model.addAttribute("currentYear", currentYear);
        model.addAttribute("currentPath", request.getRequestURI());

        return "applications/edit";
    }

    @PostMapping("/{applicationId}/edit")
    public String updateApplication(@PathVariable String applicationId,
                                    @ModelAttribute UpdateApplicationRequest request,
                                    RedirectAttributes redirectAttributes) {

        applicationService.updateApplication(applicationId, request);

        redirectAttributes.addFlashAttribute("success", "Application updated successfully.");

        return "redirect:/applications/" + applicationId;
    }

    @PostMapping("/{applicationId}/delete")
    public String deleteApplication(@PathVariable String applicationId,
                                    RedirectAttributes redirectAttributes) {

        applicationService.deleteApplication(applicationId);

        redirectAttributes.addFlashAttribute("success",
                "Application deleted successfully.");

        return "redirect:/applications";
    }

    @PostMapping("/{applicationId}/assign-partner")
    public String assignPartner(
            @PathVariable String applicationId,
            @ModelAttribute AssignBankPartnerRequest request,
            RedirectAttributes redirectAttributes) {

        try {
            applicationService.assignPartner(applicationId, request);

            redirectAttributes.addFlashAttribute("success", "Partner assigned successfully");

        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/applications/" + applicationId;
    }

    @PostMapping("/{applicationId}/update-partners")
    public String updatePartners(
            @PathVariable String applicationId,
            @ModelAttribute UpdateApplicationBankPartnersRequest request,
            RedirectAttributes redirectAttributes) {

        try {
            applicationService.updateAll(request.getApplicationId(), request);
            redirectAttributes.addFlashAttribute("success", "Partners updated successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/applications/" + applicationId;
    }
}
