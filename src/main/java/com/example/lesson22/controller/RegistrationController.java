package com.example.lesson22.controller;

import com.example.lesson22.dto.RegistrationForm;
import com.example.lesson22.model.User;
import com.example.lesson22.model.UsersBase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.concurrent.ConcurrentHashMap;

@Controller
public class RegistrationController {


    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("regForm", new RegistrationForm());
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationHandler(Model model, @ModelAttribute User user) {
        if (UsersBase.add(user)) {
            model.addAttribute("regForm", new RegistrationForm());
            model.addAttribute("user", user.getLogin());
        } else {
            model.addAttribute("error", true);
            model.addAttribute("regForm", new RegistrationForm());
        }
        return "registration";
    }
}
