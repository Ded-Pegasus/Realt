package com.innowise.realt.controllers;

import com.innowise.realt.repository.UserRepository;
import com.innowise.realt.repository.domain.User;
import com.innowise.realt.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProfileService profileService;

    @GetMapping("/profile")
    public String viewProfile(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute(user);
        return "profile";
    }

    @PostMapping("/profile")
    public String userDetails(
            @AuthenticationPrincipal User user,
            @RequestParam String mail,
            @RequestParam String name,
            @RequestParam String phone,
            Model model
    ) {

        model.addAttribute(user);
        profileService.updateMessage(model, user, mail, name, phone);
        return "profile";
    }
}
