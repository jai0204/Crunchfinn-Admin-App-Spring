package com.crunchfinn.admin.security.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

    @RequestMapping("/access-denied")
    public String accessDenied(HttpServletRequest request, Model model) {
        model.addAttribute("currentPath", request.getRequestURI());
        return "error/access-denied";
    }
}