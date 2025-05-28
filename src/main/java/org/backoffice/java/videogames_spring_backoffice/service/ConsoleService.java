package org.backoffice.java.videogames_spring_backoffice.service;

import java.util.List;
import java.util.Optional;

import org.backoffice.java.videogames_spring_backoffice.model.Console;
import org.backoffice.java.videogames_spring_backoffice.repository.ConsoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsoleService {

    @Autowired
    private ConsoleRepository consoleRepo;

    public List<Console> findAll() {
        return consoleRepo.findAll();
    }

    public Optional<Console> findById(Long id) {
        return consoleRepo.findById(id);
    }

    public Console save(Console console) {
        return consoleRepo.save(console);
    }

    public void delete(Console console) {
        consoleRepo.delete(console);
    }

    public void deleteById(Long id){
        consoleRepo.deleteById(id);
    }

    public boolean existsById(Long id) {
        return consoleRepo.existsById(id);
    }
}
