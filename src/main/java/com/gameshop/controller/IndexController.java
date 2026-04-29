package com.gameshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String inicio(Model model) {
        model.addAttribute("mensaje", "Bienvenidos a la forja de clásicos");
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}