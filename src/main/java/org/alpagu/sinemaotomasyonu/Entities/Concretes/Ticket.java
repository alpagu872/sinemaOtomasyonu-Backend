
package org.alpagu.sinemaotomasyonu.Entities.Concretes;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "ticket")
public class Ticket implements Serializable {

    @Id
    @Column(name = "ticket_id", length = 20)
    private String ticketId;

    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @Column(name = "class", length = 3, nullable = false)
    private String ticketClass;

    @Column(name = "price", nullable = false)
    private Integer price;

    // Getters and setters
}
