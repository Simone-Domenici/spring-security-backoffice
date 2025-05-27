package org.backoffice.java.videogames_spring_backoffice.service;

import java.util.List;
import java.util.Optional;

import org.backoffice.java.videogames_spring_backoffice.model.Videogame;
import org.backoffice.java.videogames_spring_backoffice.repository.VideogameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class VideogameService {

    @Autowired
    private VideogameRepository videogameRepo;

    public List<Videogame> findAll() {
        return videogameRepo.findAll();
    }

    public List<Videogame> findAll(Sort sort) {
        return videogameRepo.findAll(sort);
    }

    public Optional<Videogame> findById(Long id) {
        return videogameRepo.findById(id);
    }

    public Videogame save(Videogame vg) {
        return videogameRepo.save(vg);
    }

    public void delete(Videogame vg) {
        videogameRepo.delete(vg);
    }

    public void deleteById(Long id){
        videogameRepo.deleteById(id);
    }

    public boolean existsById(Long id) {
        return videogameRepo.existsById(id);
    }
}