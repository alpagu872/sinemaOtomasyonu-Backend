package org.alpagu.sinemaotomasyonu.Entities.Concretes;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "screen")
public class Screen implements Serializable {

    @Id
    @Column(name = "screen_id", length = 5)
    private String screenId;

    @Column(name = "no_of_seats_gold", nullable = false)
    private Integer noOfSeatsGold;

    @Column(name = "no_of_seats_silver", nullable = false)
    private Integer noOfSeatsSilver;

    @ManyToOne
    @JoinColumn(name = "theatre_id", nullable = false)
    private Theatre theatre;


    // Getters and setters
}
