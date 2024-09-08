package org.alpagu.sinemaotomasyonu.Entities.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ShowDTO {
    private String showId;
    private String showTime;
    private String showDate;
    private Integer seatsRemainingGold;
    private Integer seatsRemainingSilver;
    private Integer classCostGold;
    private Integer classCostSilver;
    private String screenId;
    private String movieId;

    // Getters and setters
}
