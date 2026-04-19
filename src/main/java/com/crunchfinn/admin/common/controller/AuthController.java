package com.crunchfinn.admin.common.controller;

import com.crunchfinn.admin.common.dto.LoginRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @PostMapping("/login")
    public String login(LoginRequest request) {
        return "redirect:/dashboard";
    }
}
