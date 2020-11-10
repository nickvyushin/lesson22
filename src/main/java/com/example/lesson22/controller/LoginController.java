package com.example.lesson22.controller;

import com.example.lesson22.dto.FormPassword;
import com.example.lesson22.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.concurrent.ConcurrentHashMap;

@Controller
public class LoginController {
    private ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>() {{
       put("vasya", new User("vasya", "1234", 18));
       put("katya", new User("katya", "katya", 16));
    }};

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("form", new FormPassword());
        return "login";
    }

    @PostMapping("/login")
    public String loginHandler(Model model, @ModelAttribute FormPassword form) {

        if (!users.containsKey(form.getLogin())) {
            model.addAttribute("error", true);
            model.addAttribute("form", new FormPassword());
            return "login";
        }
        else if (!form.getPassword().equals(users.get(form.getLogin()).getPassword())) {
            model.addAttribute("error", true);
            model.addAttribute("form", new FormPassword());
            return "login";
        }
        model.addAttribute("form", new FormPassword());

        return "redirect:/blog/" + form.getLogin() + "/?password=" + form.getPassword();
    }
}
