package org.backoffice.java.videogames_spring_backoffice.controller;

import org.backoffice.java.videogames_spring_backoffice.model.Videogame;
import org.backoffice.java.videogames_spring_backoffice.service.VideogameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/videogames")
public class VideogameController {
    @Autowired
    private VideogameService service;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("videogames", service.findAll());
        return "videogames/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("videogame", new Videogame());
        // model.addAttribute("consoles", // fetch list of consoles);
        // model.addAttribute("genres",   // fetch list of genres);
        return "videogames/form";
    }

    @PostMapping
    public String save(@ModelAttribute Videogame videogame) {
        service.save(videogame);
        return "redirect:/videogames";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("videogame", service.findById(id));
        // model.addAttribute("consoles", // fetch list of consoles);
        // model.addAttribute("genres",   // fetch list of genres);
        return "videogames/form";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(service.findById(id).get());
        return "redirect:/videogames";
    }
}