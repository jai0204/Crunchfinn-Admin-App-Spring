package com.crunchfinn.admin.disbursement.controller;

import com.crunchfinn.admin.application.enums.ApplicationSource;
import com.crunchfinn.admin.disbursement.dto.DisbursementDetailsResponse;
import com.crunchfinn.admin.disbursement.dto.DisbursementListResponse;
import com.crunchfinn.admin.disbursement.dto.UpdateDisbursementRequest;
import com.crunchfinn.admin.disbursement.service.DisbursementService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/disbursements")
public class DisbursementController {

    private final DisbursementService disbursementService;

    public DisbursementController(DisbursementService disbursementService) {
        this.disbursementService = disbursementService;
    }

    // ===============================
    // Disbursement List Page
    // ===============================
    @GetMapping
    public String listDisbursements(@RequestParam(required = false) String search,
                                    @RequestParam(required = false) String source,
                                    @RequestParam(required = false) String disbursementMonth, // yyyy-MM
                                    HttpServletRequest request,
                                    Model model) {

        List<DisbursementListResponse> disbursements = null;

        if (search != null || source != null || disbursementMonth != null) {
            disbursements = disbursementService.searchDisbursements(
                    search,
                    source,
                    disbursementMonth
            );
        } else {
            disbursements = disbursementService.getAllDisbursements();
        }

        model.addAttribute("disbursements", disbursements);
        model.addAttribute("search", search);
        model.addAttribute("source", source);
        model.addAttribute("disbursementMonth", disbursementMonth);
        model.addAttribute("sourceList", ApplicationSource.values());
        model.addAttribute("currentPath", request.getRequestURI());

        return "disbursements/list";
    }

    @PostMapping("/update")
    public String updateDisbursement(@ModelAttribute UpdateDisbursementRequest request,
                                     RedirectAttributes redirectAttributes) {

        disbursementService.updateTranches(request);

        redirectAttributes.addFlashAttribute("success", "Disbursement tranches updated successfully");

        return "redirect:/disbursements";
    }


    @GetMapping("/{id}")
    @ResponseBody
    public DisbursementDetailsResponse getDisbursement(@PathVariable Long id) {
        return disbursementService.getDetails(id);
    }
}