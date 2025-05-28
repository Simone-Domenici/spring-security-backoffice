package org.backoffice.java.videogames_spring_backoffice.controller.rest;

import java.util.List;
import java.util.Optional;

import org.backoffice.java.videogames_spring_backoffice.model.Console;
import org.backoffice.java.videogames_spring_backoffice.model.Genre;
import org.backoffice.java.videogames_spring_backoffice.model.Videogame;
import org.backoffice.java.videogames_spring_backoffice.service.GenreService;
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
@RequestMapping("/api/genres")
public class GenreRestController {

    @Autowired
    private GenreService genreService;

    @Autowired
    private VideogameService videogameService;

    @GetMapping
    public List<Genre> index() {
        return genreService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> show(@PathVariable Long id) {
        Optional<Genre> genreAttempt = genreService.findById(id);
        if (genreAttempt.isEmpty()) {
            return new ResponseEntity<Genre>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Genre>(genreAttempt.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Genre> store(@RequestBody Genre genre) {
        Genre saved = genreService.save(genre);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genre> update(@PathVariable Long id, @RequestBody Genre genre) {
        if (!genreService.existsById(id)) {
            return new ResponseEntity<Genre>(HttpStatus.NOT_FOUND);
        }
        genre.setId(id);
        Genre updated = genreService.save(genre);
        return new ResponseEntity<Genre>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Genre> delete(@PathVariable Long id) {
        if (!genreService.existsById(id)) {
            return new ResponseEntity<Genre>(HttpStatus.NOT_FOUND);
        }
        genreService.deleteById(id);
        return new ResponseEntity<Genre>(HttpStatus.NO_CONTENT);
    }

    // Endpoint per ottenere i videogame disponibili in un genere
    @GetMapping("/{id}/videogames")
    public ResponseEntity<List<Videogame>> getGamesByGenre(@PathVariable Long id) {
        Optional<Genre> genreAttempt = genreService.findById(id);
        if (genreAttempt.isEmpty()) {
            return new ResponseEntity<List<Videogame>>(HttpStatus.NOT_FOUND);
        }
        List<Videogame> games = videogameService.findByGenre(genreAttempt.get());
        return new ResponseEntity<List<Videogame>>(games, HttpStatus.OK);
    }
}

