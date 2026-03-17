package com.fidestore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/acceso_denegado")
    public String accesoDenegado(Model model) {
        return "/errores/acceso_denegado"; 
    }
}