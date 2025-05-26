package org.backoffice.java.videogames_spring_backoffice.repository;

import java.util.Optional;

import org.backoffice.java.videogames_spring_backoffice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
    
    Optional<User> findByUsername( String username);
}
