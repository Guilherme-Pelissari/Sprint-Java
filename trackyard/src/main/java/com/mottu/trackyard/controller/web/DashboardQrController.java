package com.mottu.trackyard.controller.web;

import com.mottu.trackyard.service.PontosLeituraService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.data.domain.PageRequest; // 1. ADICIONE ESTE IMPORT

@Controller
public class DashboardQrController {

    private final PontosLeituraService pontosLeituraService;

    public DashboardQrController(PontosLeituraService pontosLeituraService) {
        this.pontosLeituraService = pontosLeituraService;
    }

    @GetMapping("/dashboard/gerar")
    public String gerarPage(Model model) {
        return "dashboard/gerar"; 
    }

    @GetMapping("/dashboard/ler")
    public String lerPage(Model model) {
        
        // 2. CRIE UM PAGE REQUEST (Ex: página 0, 100 itens)
        PageRequest pageRequest = PageRequest.of(0, 100); 

        // 3. PASSE O PAGE REQUEST EM VEZ DE 'null'
        var pontos = pontosLeituraService.getAllPontosLeitura(pageRequest);
        model.addAttribute("listaDePontos", pontos.getContent());
        
        return "dashboard/ler"; 
    }
}