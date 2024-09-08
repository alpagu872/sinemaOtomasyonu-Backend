package org.alpagu.sinemaotomasyonu.Business.Concretes;


import org.alpagu.sinemaotomasyonu.Business.Abstracts.TheatreService;
import org.alpagu.sinemaotomasyonu.DataAccess.Abstracts.TheatreRepository;
import org.alpagu.sinemaotomasyonu.Entities.Concretes.Theatre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheatreServiceImpl implements TheatreService {

    private final TheatreRepository theatreRepository;

    @Autowired
    public TheatreServiceImpl(TheatreRepository theatreRepository) {
        this.theatreRepository = theatreRepository;
    }

    @Override
    public Theatre saveTheatre(Theatre theatre) {
        return theatreRepository.save(theatre);
    }

    @Override
    public Theatre updateTheatre(String theatreId, Theatre theatre) {
        Theatre existingTheatre = theatreRepository.findById(theatreId)
                .orElseThrow(() -> new RuntimeException("Theatre not found"));
        existingTheatre.setNameOfTheatre(theatre.getNameOfTheatre());
        existingTheatre.setNoOfScreens(theatre.getNoOfScreens());
        existingTheatre.setArea(theatre.getArea());
        return theatreRepository.save(existingTheatre);
    }

    @Override
    public Theatre getTheatreById(String theatreId) {
        return theatreRepository.findById(theatreId)
                .orElseThrow(() -> new RuntimeException("Theatre not found"));
    }

    @Override
    public List<Theatre> getAllTheatres() {
        return theatreRepository.findAll();
    }

    @Override
    public void deleteTheatre(String theatreId) {
        theatreRepository.deleteById(theatreId);
    }
}
