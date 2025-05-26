package org.backoffice.java.videogames_spring_backoffice.controller;

import org.backoffice.java.videogames_spring_backoffice.model.Videogame;
import org.backoffice.java.videogames_spring_backoffice.service.ConsoleService;
import org.backoffice.java.videogames_spring_backoffice.service.GenreService;
import org.backoffice.java.videogames_spring_backoffice.service.VideogameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;



@Controller
@RequestMapping("/videogames")
public class VideogameController {
    @Autowired
    private VideogameService service;

    @Autowired
    private ConsoleService consoleService;

    @Autowired
    private GenreService genreService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("videogames", service.findAll());
        return "videogames/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model) {
        // recupera il singolo videogame e lo mette in "videogame"
        Videogame vg = service.findById(id)
            .orElseThrow(() -> new RuntimeException("Videogame not found: " + id));
        model.addAttribute("videogame", vg);
        return "videogames/show";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("videogame", new Videogame());
        model.addAttribute("consoles", consoleService.findAll());
        model.addAttribute("genres", genreService.findAll());
        return "videogames/form";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("videogame") Videogame formVideogame, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("consoles", consoleService.findAll());
            model.addAttribute("genre", genreService.findAll());
            return "videogames/form";
        }

        service.save(formVideogame);

        return "redirect:/videogames";
    }

    @PostMapping
    public String save(@ModelAttribute Videogame videogame) {
        service.save(videogame);
        return "redirect:/videogames";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("videogame", service.findById(id).get());
        model.addAttribute("consoles", consoleService.findAll());
        model.addAttribute("genres",  genreService.findAll() );
        return "videogames/form";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("videogame") Videogame forVideogame, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("consoles", consoleService.findAll());
            model.addAttribute("genres", genreService.findAll());
            return "videogames/form";
        }
        
        service.save(forVideogame);

        return "redirect:/videogames";
    }
    

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(service.findById(id).get());
        return "redirect:/videogames";
    }
}