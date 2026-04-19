package com.crunchfinn.admin.common.controller;

import com.crunchfinn.admin.common.dto.LoginRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String landingPage(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());

        return "pages/landing";
    }

    @GetMapping("/dashboard")
    public String dashboardPage(HttpServletRequest request, Model model) {
        model.addAttribute("currentPath", request.getRequestURI());

        return "pages/dashboard";
    }
}
