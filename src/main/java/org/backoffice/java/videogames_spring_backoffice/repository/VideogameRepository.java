package org.backoffice.java.videogames_spring_backoffice.repository;

import java.util.List;

import org.backoffice.java.videogames_spring_backoffice.model.Console;
import org.backoffice.java.videogames_spring_backoffice.model.Genre;
import org.backoffice.java.videogames_spring_backoffice.model.Videogame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideogameRepository extends JpaRepository<Videogame, Long> {
    List<Videogame> findAllByConsolesContains(Console console);
    List<Videogame> findAllByGenresContains(Genre genre);
}