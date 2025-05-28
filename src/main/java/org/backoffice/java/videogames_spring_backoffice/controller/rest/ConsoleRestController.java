package org.backoffice.java.videogames_spring_backoffice.controller.rest;

import java.util.List;
import java.util.Optional;

import org.backoffice.java.videogames_spring_backoffice.model.Console;
import org.backoffice.java.videogames_spring_backoffice.model.Videogame;
import org.backoffice.java.videogames_spring_backoffice.service.ConsoleService;
import org.backoffice.java.videogames_spring_backoffice.service.VideogameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/consoles")
public class ConsoleRestController {

    @Autowired
    private ConsoleService consoleService;

    @Autowired
    private VideogameService videogameService;

    @GetMapping
    public List<Console> index() {
        return consoleService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Console> show(@PathVariable Long id) {
        Optional<Console> consoleAttempt = consoleService.findById(id);
        if (consoleAttempt.isEmpty()) {
            return new ResponseEntity<Console>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Console>(consoleAttempt.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Console> store(@RequestBody Console console) {
        Console saved = consoleService.save(console);
        return new ResponseEntity<Console>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Console> update(@PathVariable Long id, @RequestBody Console console) {
        if (!consoleService.existsById(id)) {
            return new ResponseEntity<Console>(HttpStatus.NOT_FOUND);
        }
        console.setId(id);
        Console updated = consoleService.save(console);
        return new ResponseEntity<Console>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Console> delete(@PathVariable Long id) {
        if (!consoleService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        consoleService.deleteById(id);
        return new ResponseEntity<Console>(HttpStatus.NO_CONTENT);
    }

    // Endpoint per ottenere i videogame disponibili su una console
    @GetMapping("/{id}/videogames")
    public ResponseEntity<List<Videogame>> getGamesByConsole(@PathVariable Long id) {
        Optional<Console> consoleAttempt = consoleService.findById(id);
        if (consoleAttempt.isEmpty()) {
            return new ResponseEntity<List<Videogame>>(HttpStatus.NOT_FOUND);
        }
        List<Videogame> games = videogameService.findByConsole(consoleAttempt.get());
        return new ResponseEntity<List<Videogame>>(games, HttpStatus.OK);
    }
}
