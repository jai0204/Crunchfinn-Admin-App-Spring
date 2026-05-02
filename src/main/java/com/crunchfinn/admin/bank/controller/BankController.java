package com.crunchfinn.admin.bank.controller;

import com.crunchfinn.admin.bank.dto.CreateBankRequest;
import com.crunchfinn.admin.bank.dto.UpdateBankRequest;
import com.crunchfinn.admin.bank.dto.BankResponse;
import com.crunchfinn.admin.bank.service.BankService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/banks")
public class BankController {

    private final BankService bankService;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    // LIST PAGE
//    @GetMapping
//    public String listBanks(HttpServletRequest request,
//                            Model model) {
//
//        List<BankResponse> banks = bankService.getAllBanks();
//
//        model.addAttribute("banks", banks);
//        model.addAttribute("currentPath", request.getRequestURI());
//
//        return "banks/list";
//    }

    @GetMapping
    public String listBanks(@RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "10") int size,
                            HttpServletRequest request,
                            Model model) {

        Page<BankResponse> bankPage = bankService.getAllBanks(page, size);

        model.addAttribute("banks", bankPage.getContent());
        model.addAttribute("currentPath", request.getRequestURI());
        // Pagination
        int currentPage = bankPage.getNumber();
        long totalItems = bankPage.getTotalElements();
        long start = totalItems == 0 ? 0 : (long) currentPage * size + 1;
        long end = totalItems == 0 ? 0 : Math.min((currentPage + 1L) * size, totalItems);

        model.addAttribute("start", start);
        model.addAttribute("end", end);
        model.addAttribute("currentPage", bankPage.getNumber());
        model.addAttribute("totalItems", bankPage.getTotalElements());
        model.addAttribute("totalPages", bankPage.getTotalPages());
        model.addAttribute("size", size);

        return "banks/list";
    }

    // CREATE PAGE
    @GetMapping("/new")
    public String createPage(HttpServletRequest request,
                             Model model) {

        model.addAttribute("form", new CreateBankRequest());
        model.addAttribute("currentPath", request.getRequestURI());

        return "banks/create";
    }

    // CREATE SUBMIT
    @PostMapping
    public String createBank(@ModelAttribute("form") CreateBankRequest request,
                             RedirectAttributes redirectAttributes) {

        BankResponse bank = bankService.createBank(request);

        redirectAttributes.addFlashAttribute("success",
                "Bank created successfully");

        return "redirect:/banks/" + bank.getBankCode();
    }

    // VIEW PAGE
    @GetMapping("/{code}")
    public String viewBank(@PathVariable String code,
                           HttpServletRequest request,
                           Model model) {

        BankResponse bank = bankService.getByBankCode(code);

        model.addAttribute("bank", bank);
        model.addAttribute("currentPath", request.getRequestURI());

        return "banks/view";
    }

    // EDIT PAGE
    @GetMapping("/{code}/edit")
    public String editPage(@PathVariable String code,
                           HttpServletRequest request,
                           Model model) {

        BankResponse bank = bankService.getByBankCode(code);

        model.addAttribute("form", bank);
        model.addAttribute("currentPath", request.getRequestURI());

        return "banks/edit";
    }

    // EDIT SUBMIT
    @PostMapping("/{code}/edit")
    public String updateBank(
            @PathVariable String code,
            @ModelAttribute("form") UpdateBankRequest request,
            RedirectAttributes redirectAttributes) {

        bankService.updateBank(code, request);

        redirectAttributes.addFlashAttribute("success",
                "Bank updated successfully");

        return "redirect:/banks/" + code;
    }

    // DELETE
    @PostMapping("/{code}/delete")
    public String deleteBank(
            @PathVariable String code,
            RedirectAttributes redirectAttributes) {

        bankService.deleteBank(code);

        redirectAttributes.addFlashAttribute("success",
                "Bank deleted successfully");

        return "redirect:/banks";
    }
}