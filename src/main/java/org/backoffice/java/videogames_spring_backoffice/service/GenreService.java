package org.backoffice.java.videogames_spring_backoffice.service;

import java.util.List;
import java.util.Optional;

import org.backoffice.java.videogames_spring_backoffice.model.Genre;
import org.backoffice.java.videogames_spring_backoffice.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepo;

    public List<Genre> findAll() {
        return genreRepo.findAll();
    }

    public Optional<Genre> findById(Long id) {
        return genreRepo.findById(id);
    }

    public Genre save(Genre genre) {
        return genreRepo.save(genre);
    }

    public void delete(Genre genre) {
        genreRepo.delete(genre);
    }

    public void deleteById(Long id){
        genreRepo.deleteById(id);
    }

    public boolean existsById(Long id) {
        return genreRepo.existsById(id);
    }
}