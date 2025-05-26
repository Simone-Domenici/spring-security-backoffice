package org.backoffice.java.videogames_spring_backoffice.controller;

import org.backoffice.java.videogames_spring_backoffice.model.Console;
import org.backoffice.java.videogames_spring_backoffice.service.ConsoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/consoles")
public class ConsoleController {
    @Autowired
    private ConsoleService service;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("consoles", service.findAll());
        return "consoles/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("console", new Console());
        return "consoles/form";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("console") Console formConsole, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "consoles/form";
        }      
        
        service.save(formConsole);
        
        return "redirect:/consoles";
    }
    

    @PostMapping
    public String save(@ModelAttribute Console console) {
        service.save(console);
        return "redirect:/consoles";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("console", service.findById(id).orElseThrow());
        return "consoles/form";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("console") Console formConsole, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "consoles/form";
        }

        service.save(formConsole);
        
        return "redirect:/consoles";
    }
    

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.findById(id).ifPresent(service::delete);
        return "redirect:/consoles";
    }
}
