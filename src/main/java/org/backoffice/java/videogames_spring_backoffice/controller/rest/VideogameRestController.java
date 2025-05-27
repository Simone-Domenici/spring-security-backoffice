package org.backoffice.java.videogames_spring_backoffice.controller.rest;

import java.util.List;
import java.util.Optional;

import org.backoffice.java.videogames_spring_backoffice.model.Videogame;
import org.backoffice.java.videogames_spring_backoffice.service.VideogameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/videogames")
public class VideogameRestController {
    
    @Autowired
    private VideogameService videogameService;

    @GetMapping
    public List<Videogame> index() {
        return videogameService.findAll();
    }

    @GetMapping("/sorted")
    public List<Videogame> findAllSorted(@RequestParam Optional<String> sortBy) {
        if (sortBy.isPresent()) {
            String key = sortBy.get();
            if ("name".equalsIgnoreCase(key)) {
                return videogameService.findAll(Sort.by("name"));
            } else if ("metacritic".equalsIgnoreCase(key)) {
                return videogameService.findAll(Sort.by(Sort.Direction.DESC, "metacriticScore"));
            }
        }
        
        return videogameService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Videogame> show(@PathVariable Long id) {
        Optional<Videogame> vgAttempt = videogameService.findById(id);
        if (vgAttempt.isEmpty()) {
            return new ResponseEntity<Videogame>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Videogame>(vgAttempt.get(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Videogame> store(@RequestBody Videogame videogame) {
        
        return new ResponseEntity<Videogame>(videogameService.save(videogame),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Videogame> update(@PathVariable Long id, @RequestBody Videogame videogame) {
        if (!videogameService.existsById(id)) {
            return new ResponseEntity<Videogame>(HttpStatus.NOT_FOUND);
        }
        
        videogame.setId(id);

        return new ResponseEntity<Videogame>(videogameService.save(videogame), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Videogame> delete(@PathVariable Long id){
        if (videogameService.findById(id).isEmpty()) {
            return new ResponseEntity<Videogame>(HttpStatus.NOT_FOUND);
        }

        videogameService.deleteById(id);
        return new ResponseEntity<Videogame>(HttpStatus.NO_CONTENT);
    }
    

}
