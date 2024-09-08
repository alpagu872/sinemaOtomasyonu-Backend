package org.alpagu.sinemaotomasyonu.Business.Abstracts;



import org.alpagu.sinemaotomasyonu.Entities.Dtos.ShowDTO;
import org.alpagu.sinemaotomasyonu.Entities.Concretes.Show;

import java.util.List;

public interface ShowService {
    Show saveShow(Show show);
    Show updateShow(String showId, Show show);
    Show getShowById(String showId);

    List<Show> findByMovieId(String movieId);
    List<Show> getAllShows();
    void deleteShow(String showId);
    public Show createShow(ShowDTO showDTO);
}
