package com.crunchfinn.admin.security.controller;

import com.crunchfinn.admin.security.dto.CreateUserRequest;
import com.crunchfinn.admin.security.enums.Role;
import com.crunchfinn.admin.security.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/create")
    public String createUserPage(HttpServletRequest request, Model model) {
        model.addAttribute("user", new CreateUserRequest());
        model.addAttribute("roles", Role.values());
        model.addAttribute("currentPath", request.getRequestURI());
        return "users/create";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute CreateUserRequest request,
                             RedirectAttributes redirectAttributes) {

        try {
            userService.createUser(request);
            redirectAttributes.addFlashAttribute("success", "User created successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/admin/users/create";
        }

        return "redirect:/settings";
    }
}