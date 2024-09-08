package org.alpagu.sinemaotomasyonu.Entities.Concretes;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@Table(name = "movie")
public class Movie implements Serializable {

    @Id
    @Column(name = "movie_id", length = 5)
    private String movieId;

    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @Column(name = "language", length = 10)
    private String language;

    @Column(name = "genre", length = 20)
    private String genre;

    @Column(name = "target_audience", length = 5)
    private String targetAudience;


    // Getters and setters

    @PrePersist
    private void prePersist() {
        if (this.movieId == null || this.movieId.isEmpty()) {
            this.movieId = UUID.randomUUID().toString().substring(0, 5);
            System.out.println("Generated Movie ID: " + this.movieId); // Debug log
        }
    }


}

