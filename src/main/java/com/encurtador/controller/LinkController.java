package com.encurtador.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.encurtador.model.LinkModel;
import com.encurtador.service.LinkService;

@Controller
public class LinkController {

    private final LinkService linkService;
    
    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/encurtar")
    public String encurtar(@ModelAttribute LinkModel linkModel, org.springframework.ui.Model model) {

        LinkModel salvo = linkService.salvar(linkModel);
        String urlCompleta = "http://localhost:8080/" + salvo.getCodigo();
        model.addAttribute("shortUrl", urlCompleta);
        return "index";
    }

    @GetMapping("/{codigo}")
    public String redirecionar(@PathVariable String codigo) {

        LinkModel linkBuscado = linkService.buscarPorCodigo(codigo);
        
        if (linkBuscado != null) {
            return "redirect:" + linkBuscado.getUrl();
        }
        return "index";
    }
}