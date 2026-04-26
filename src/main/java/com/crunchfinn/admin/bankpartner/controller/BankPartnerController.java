package com.crunchfinn.admin.bankpartner.controller;

import com.crunchfinn.admin.bank.dto.BankResponse;
import com.crunchfinn.admin.bank.service.BankService;
import com.crunchfinn.admin.bankpartner.dto.CreateBankPartnerRequest;
import com.crunchfinn.admin.bankpartner.dto.UpdateBankPartnerRequest;
import com.crunchfinn.admin.bankpartner.dto.BankPartnerResponse;
import com.crunchfinn.admin.bankpartner.service.BankPartnerService;
import com.crunchfinn.admin.common.enums.Gender;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/partners")
public class BankPartnerController {

    private final BankPartnerService partnerService;
    private final BankService bankService;

    public BankPartnerController(BankPartnerService partnerService,
                                 BankService bankService) {
        this.partnerService = partnerService;
        this.bankService = bankService;
    }

    // LIST PAGE
    @GetMapping
    public String listPartners(HttpServletRequest request, Model model) {

        List<BankPartnerResponse> partners = partnerService.getAllPartners();

        model.addAttribute("partners", partners);
        model.addAttribute("currentPath", request.getRequestURI());

        return "partners/list";
    }

    // CREATE PAGE
    @GetMapping("/new")
    public String createPage(HttpServletRequest request, Model model) {

        model.addAttribute("form", new CreateBankPartnerRequest());
        model.addAttribute("genderList", Gender.values());
        model.addAttribute("currentPath", request.getRequestURI());

        // for dropdown (select bank)
        List<BankResponse> banks = bankService.getAllBanks();
        model.addAttribute("banks", banks);

        return "partners/create";
    }

    // CREATE SUBMIT
    @PostMapping
    public String createPartner(
            @ModelAttribute("form") CreateBankPartnerRequest request,
            RedirectAttributes redirectAttributes) {

        BankPartnerResponse partner = partnerService.createPartner(request);

        redirectAttributes.addFlashAttribute("success",
                "Partner created successfully");

        return "redirect:/partners/" + partner.getBankPartnerId();
    }

    // VIEW PAGE
    @GetMapping("/{partnerId}")
    public String viewPartner(@PathVariable String partnerId,
                              HttpServletRequest request,
                              Model model) {

        BankPartnerResponse partner = partnerService.getByPartnerId(partnerId);

        model.addAttribute("partner", partner);
        model.addAttribute("currentPath", request.getRequestURI());

        return "partners/view";
    }

    // EDIT PAGE
    @GetMapping("/{partnerId}/edit")
    public String editPage(@PathVariable String partnerId,
                           HttpServletRequest request,
                           Model model) {

        BankPartnerResponse partner = partnerService.getByPartnerId(partnerId);

        model.addAttribute("form", partner);
        model.addAttribute("genderList", Gender.values());
        model.addAttribute("currentPath", request.getRequestURI());

        // dropdown again
        List<BankResponse> banks = bankService.getAllBanks();
        model.addAttribute("banks", banks);

        return "partners/edit";
    }

    // EDIT SUBMIT
    @PostMapping("/{partnerId}/edit")
    public String updatePartner(
            @PathVariable String partnerId,
            @ModelAttribute("form") UpdateBankPartnerRequest request,
            RedirectAttributes redirectAttributes) {

        request.setBankPartnerId(partnerId);

        partnerService.updatePartner(request);

        redirectAttributes.addFlashAttribute("success",
                "Partner updated successfully");

        return "redirect:/partners/" + partnerId;
    }

    // DELETE
    @PostMapping("/{id}/delete")
    public String deletePartner(
            @PathVariable Long id,
            @RequestHeader(value = "Referer", required = false) String referer,
            RedirectAttributes redirectAttributes) {

        partnerService.deletePartner(id);

        redirectAttributes.addFlashAttribute("success",
                "Partner deleted successfully");

        return "redirect:" + (referer != null ? referer : "/partners");
    }
}