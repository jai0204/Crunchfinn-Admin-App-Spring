package com.crunchfinn.admin.bank.controller;

import com.crunchfinn.admin.bank.dto.CreateBankRequest;
import com.crunchfinn.admin.bank.dto.UpdateBankRequest;
import com.crunchfinn.admin.bank.dto.BankResponse;
import com.crunchfinn.admin.bank.service.BankService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping
    public String listBanks(HttpServletRequest request,
                            Model model) {

        List<BankResponse> banks = bankService.getAllBanks();

        model.addAttribute("banks", banks);
        model.addAttribute("currentPath", request.getRequestURI());

        return "banks/list";
    }

}