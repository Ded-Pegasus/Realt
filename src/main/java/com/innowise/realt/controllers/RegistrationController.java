package com.innowise.realt.controllers;

import com.innowise.realt.model.ValidFunctions;
import com.innowise.realt.repository.domain.User;
import com.innowise.realt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@Valid User user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = ValidFunctions.getErrors(bindingResult);
            model.mergeAttributes(errorMap);
            return "registration";
        }

        if (userService.addUser(user)) {
            return "redirect:/login";
        } else {
            model.addAttribute("usernameError", "user exist!");
            return "registration";
        }
    }
}
