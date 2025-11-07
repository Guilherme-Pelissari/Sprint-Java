package com.mottu.trackyard.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardQrController {

    @GetMapping("/dashboard/gerar")
    public String gerarPage(Model model) {
        // se quiser, insira dados no model
        return "dashboard/gerar"; // templates/dashboard/gerar.html
    }

    @GetMapping("/dashboard/ler")
    public String lerPage(Model model) {
        return "dashboard/ler"; // templates/dashboard/ler.html
    }
}