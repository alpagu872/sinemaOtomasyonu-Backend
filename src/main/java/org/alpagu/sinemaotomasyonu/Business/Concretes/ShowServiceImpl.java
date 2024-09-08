package org.alpagu.sinemaotomasyonu.Business.Concretes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.alpagu.sinemaotomasyonu.Business.Abstracts.ShowService;
import org.alpagu.sinemaotomasyonu.Entities.Dtos.ShowDTO;
import org.alpagu.sinemaotomasyonu.Core.utilities.ExceptionHandling.ErrorDetails;
import org.alpagu.sinemaotomasyonu.DataAccess.Abstracts.MovieRepository;
import org.alpagu.sinemaotomasyonu.DataAccess.Abstracts.ScreenRepository;
import org.alpagu.sinemaotomasyonu.DataAccess.Abstracts.ShowRepository;
import org.alpagu.sinemaotomasyonu.Entities.Concretes.Movie;
import org.alpagu.sinemaotomasyonu.Entities.Concretes.Screen;
import org.alpagu.sinemaotomasyonu.Entities.Concretes.Show;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ShowServiceImpl implements ShowService {
    @PersistenceContext
    private EntityManager entityManager;

    private final ShowRepository showRepository;

    @Autowired
    private ScreenRepository screenRepository;

    @Autowired
    private MovieRepository movieRepository;


    @Override
    public Show createShow(ShowDTO showDTO) {
        Screen screen = screenRepository.findById(showDTO.getScreenId())
                .orElseThrow(() -> new ErrorDetails.ResourceNotFoundException("Screen not found with id " + showDTO.getScreenId()));
        Movie movie = movieRepository.findById(showDTO.getMovieId())
                .orElseThrow(() -> new ErrorDetails.ResourceNotFoundException("Movie not found with id " + showDTO.getMovieId()));

        Show show = new Show();
        show.setShowTime(LocalTime.parse(showDTO.getShowTime()));
        show.setShowDate(LocalDate.parse(showDTO.getShowDate()));
        show.setSeatsRemainingGold(showDTO.getSeatsRemainingGold());
        show.setSeatsRemainingSilver(showDTO.getSeatsRemainingSilver());
        show.setClassCostGold(showDTO.getClassCostGold());
        show.setClassCostSilver(showDTO.getClassCostSilver());
        show.setScreen(screen);
        show.setMovie(movie);

        // ID oluşturma mantığı burada
        String prefix = "SHT";
        Long sequenceValue = (Long) entityManager.createNativeQuery("SELECT nextval('show_id_seq')")
                .getSingleResult();
        show.setShowId(String.format("%s%07d", prefix, sequenceValue));

        // Show entity'sini kaydediyoruz
        return showRepository.save(show);
    }

    @Autowired
    public ShowServiceImpl(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    @Override
    public Show saveShow(Show show) {
        return showRepository.save(show);
    }

    @Override
    public Show updateShow(String showId, Show show) {
        Show existingShow = showRepository.findById(showId)
                .orElseThrow(() -> new RuntimeException("Show not found"));
        existingShow.setShowTime(show.getShowTime());
        existingShow.setShowDate(show.getShowDate());
        existingShow.setSeatsRemainingGold(show.getSeatsRemainingGold());
        existingShow.setSeatsRemainingSilver(show.getSeatsRemainingSilver());
        existingShow.setClassCostGold(show.getClassCostGold());
        existingShow.setClassCostSilver(show.getClassCostSilver());
        existingShow.setScreen(show.getScreen());
        existingShow.setMovie(show.getMovie());
        return showRepository.save(existingShow);
    }

    @Override
    public Show getShowById(String showId) {
        return showRepository.findById(showId)
                .orElseThrow(() -> new RuntimeException("Show not found"));
    }

    @Override
    public List<Show> findByMovieId(String movieId) {
        return this.showRepository.findByMovie_MovieId(movieId);
    }


    @Override
    public List<Show> getAllShows() {
        return showRepository.findAll();
    }

    @Override
    public void deleteShow(String showId) {
        showRepository.deleteById(showId);
    }
}
