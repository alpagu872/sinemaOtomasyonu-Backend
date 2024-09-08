package org.alpagu.sinemaotomasyonu.Entities.Concretes;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Random;
import java.util.UUID;

@Entity
@Data
@Table(name = "booking")
public class Booking implements Serializable {

    @Id
    @Column(name = "booking_id", length = 10)
    private String bookingId;

    @Column(name = "no_of_tickets", nullable = false)
    private Integer noOfTickets;

    @Column(name = "total_cost", nullable = false)
    private Integer totalCost;

    @Column(name = "card_number", length = 19)
    private String cardNumber;

    @Column(name = "name_on_card", length = 21)
    private String nameOnCard;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;

    // Getters and setters

    @PrePersist
    private void prePersist() {
        if (this.bookingId == null || this.bookingId.isEmpty()) {
            this.bookingId = generateBookingId();
        }
    }

    private String generateBookingId() {
        // Prefix: "BK"
        String prefix = "BK";

        // Random 3-digit number for the branch code (e.g., 102)
        Random random = new Random();
        int branchCode = 100 + random.nextInt(900);

        // Random 5-digit number for the unique ID
        int uniqueId = 10000 + random.nextInt(90000);

        // Combine parts to form the booking ID
        return prefix + branchCode + uniqueId;
    }
}
