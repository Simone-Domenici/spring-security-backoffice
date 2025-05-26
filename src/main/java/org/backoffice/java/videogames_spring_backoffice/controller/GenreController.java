package org.backoffice.java.videogames_spring_backoffice.controller;

import org.backoffice.java.videogames_spring_backoffice.model.Genre;
import org.backoffice.java.videogames_spring_backoffice.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/genres")
public class GenreController {
    @Autowired
    private GenreService service;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("genres", service.findAll());
        return "genres/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("genre", new Genre());
        return "genres/form";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("genre") Genre formGenre, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "genres/form";
        }      
        
        service.save(formGenre);
        
        return "redirect:/genres";
    }

    @PostMapping
    public String save(@ModelAttribute Genre genre) {
        service.save(genre);
        return "redirect:/genres";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("genre", service.findById(id).orElseThrow());
        return "genres/form";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("genre") Genre formGenre, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "genres/form";
        }

        service.save(formGenre);
        
        return "redirect:/genres";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        Genre genreToDelete = service.findById(id).get();

        if (genreToDelete.getVideogames() != null) {
            genreToDelete.getVideogames().forEach(videogame -> {
                videogame.getGenres().remove(genreToDelete);
            });
        }

        service.delete(genreToDelete);
        
        return "redirect:/genres";
    }
}