package com.example.lesson22.controller;

import com.example.lesson22.dto.FormBlog;
import com.example.lesson22.dto.FormPassword;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CopyOnWriteArrayList;

@Controller
public class BlogController {

    private CopyOnWriteArrayList<FormBlog> blogs = new CopyOnWriteArrayList<>();

    @GetMapping("/blog/{login}")
    public String blogPage(Model model, @PathVariable("login")String login, @RequestParam("password")String password) {
        model.addAttribute("request", new FormPassword(login, password));
        model.addAttribute("form", new FormBlog());
        return "blog";
    }

    @PostMapping("/blog/{login}")
    public String blogHandlerPage(Model model, @PathVariable("login")String login, @RequestParam("password")String password, @ModelAttribute FormBlog form) {
        blogs.add(form);
        System.out.println("Новость успешно добавлена!");
        model.addAttribute("form", new FormBlog());
        return "redirect:/login";
    }

}
