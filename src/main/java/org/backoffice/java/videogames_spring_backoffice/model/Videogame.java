package org.backoffice.java.videogames_spring_backoffice.model;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Set;


@Entity
@Table(name = "videogame")
public class Videogame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    @NotBlank(message = "The name cannot be blank, empty or null")
    private String name;

    @Column(nullable = false, name = "developed_by", length = 100)
    @NotBlank(message = "The Developer's name cannot be blank, empty or null")
    private String developedBy;

    @Column(nullable = false, name = "published_by", length = 100)
    @NotBlank(message = "The Publisher's name cannot be blank, empty or null")
    private String publishedBy;

    @Column(nullable = false, name = "release_date")
    @NotNull(message = "The release date cannot be null")
    private LocalDate releaseDate;

    @Column(nullable = false, name = "metacritic_score")
    @NotNull(message = "The score cannot be null")
    @DecimalMin(value = "0.0", inclusive = true, message = "The score cannot be less than 0")
    @DecimalMax(value = "100.0", inclusive = true, message = "The score cannot exceed 100")
    private Double metacriticScore;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "videogame_console",
        joinColumns = @JoinColumn (name = "videogame_id"),
        inverseJoinColumns = @JoinColumn(name = "console_id")
        )
    private Set<Console> consoles;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "videogame_genre",
        joinColumns = @JoinColumn(name = "videogame_id"),
        inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres;

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDevelopedBy() {
        return developedBy;
    }

    public void setDevelopedBy(String developedBy) {
        this.developedBy = developedBy;
    }

    public String getPublishedBy() {
        return publishedBy;
    }

    public void setPublishedBy(String publishedBy) {
        this.publishedBy = publishedBy;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getMetacriticScore() {
        return metacriticScore;
    }

    public void setMetacriticScore(Double metacriticScore) {
        this.metacriticScore = metacriticScore;
    }

    public Set<Console> getConsoles() {
        return consoles;
    }

    public void setConsoles(Set<Console> consoles) {
        this.consoles = consoles;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    
}