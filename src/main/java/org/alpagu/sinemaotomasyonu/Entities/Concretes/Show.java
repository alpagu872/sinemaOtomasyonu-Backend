package org.alpagu.sinemaotomasyonu.Entities.Concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "show")
public class Show implements Serializable {

    @Id
    @Column(name = "show_id", length = 10)
    private String showId;

    @Column(name = "show_time", nullable = false)
    private LocalTime showTime;

    @Column(name = "show_date", nullable = false)
    private LocalDate showDate;

    @Column(name = "seats_remaining_gold", nullable = false)
    private Integer seatsRemainingGold;

    @Column(name = "seats_remaining_silver", nullable = false)
    private Integer seatsRemainingSilver;

    @Column(name = "class_cost_gold", nullable = false)
    private Integer classCostGold;

    @Column(name = "class_cost_silver", nullable = false)
    private Integer classCostSilver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screen_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Screen screen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Movie movie;


}
