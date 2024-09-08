package org.alpagu.sinemaotomasyonu.Business.Abstracts;



import org.alpagu.sinemaotomasyonu.Entities.Concretes.Theatre;

import java.util.List;

public interface TheatreService {
    Theatre saveTheatre(Theatre theatre);
    Theatre updateTheatre(String theatreId, Theatre theatre);
    Theatre getTheatreById(String theatreId);
    List<Theatre> getAllTheatres();
    void deleteTheatre(String theatreId);
}
