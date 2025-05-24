package org.backoffice.java.videogames_spring_backoffice.repository;

import org.backoffice.java.videogames_spring_backoffice.model.Videogame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideogameRepository extends JpaRepository<Videogame, Long> {
}