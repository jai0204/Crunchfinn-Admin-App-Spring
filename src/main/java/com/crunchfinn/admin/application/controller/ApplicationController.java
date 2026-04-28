package com.crunchfinn.admin.application.controller;

import com.crunchfinn.admin.application.dto.*;
import com.crunchfinn.admin.application.enums.*;
import com.crunchfinn.admin.application.service.ApplicationService;
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

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
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
}
