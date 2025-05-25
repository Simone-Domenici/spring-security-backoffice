package org.backoffice.java.videogames_spring_backoffice.repository;


import org.backoffice.java.videogames_spring_backoffice.model.Console;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsoleRepository extends JpaRepository<Console, Long> {
}