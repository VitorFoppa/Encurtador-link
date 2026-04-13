package com.encurtador.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.encurtador.model.LinkModel;
import com.encurtador.service.LinkService;

@Controller
public class LinkController {

    private final LinkService linkService;
    
    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("links", linkService.listarTodos());
        return "index";
    }

    @PostMapping("/encurtar")
    public String encurtar(@ModelAttribute LinkModel linkModel, RedirectAttributes redirectAttributes) {
        LinkModel salvo = linkService.salvar(linkModel);
        String shortUrl = "http://localhost:8080/" + salvo.getCodigo();
        redirectAttributes.addFlashAttribute("shortUrl", shortUrl);
        return "redirect:/";
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