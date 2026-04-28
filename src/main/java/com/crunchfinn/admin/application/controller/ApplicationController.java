package com.crunchfinn.admin.application.controller;

import com.crunchfinn.admin.application.dto.*;
import com.crunchfinn.admin.application.enums.*;
import com.crunchfinn.admin.application.service.ApplicationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

}
