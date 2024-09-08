package org.alpagu.sinemaotomasyonu.Entities.Concretes;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "theatre")
public class Theatre implements Serializable {

    @Id
    @Column(name = "theatre_id", length = 5)
    private String theatreId;

    @Column(name = "name_of_theatre", length = 30, nullable = false)
    private String nameOfTheatre;

    @Column(name = "no_of_screens")
    private Integer noOfScreens;

    @Column(name = "area", length = 30)
    private String area;

    // Getters and setters
}
