package org.alpagu.sinemaotomasyonu.Api.Controllers;

import org.alpagu.sinemaotomasyonu.Business.Abstracts.ShowService;
import org.alpagu.sinemaotomasyonu.Entities.Dtos.ShowDTO;
import org.alpagu.sinemaotomasyonu.DataAccess.Abstracts.MovieRepository;
import org.alpagu.sinemaotomasyonu.DataAccess.Abstracts.ScreenRepository;
import org.alpagu.sinemaotomasyonu.Entities.Concretes.Show;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shows")
public class ShowController {

    private final ShowService showService;



    @Autowired
    private ScreenRepository screenRepository;

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    @PostMapping
    public ResponseEntity<Show> createShow(@RequestBody ShowDTO showDTO) {
        Show savedShow = showService.createShow(showDTO);
        return ResponseEntity.ok(savedShow);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Show> updateShow(@PathVariable String id, @RequestBody Show show) {
        Show updatedShow = showService.updateShow(id, show);
        return ResponseEntity.ok(updatedShow);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Show> getShowById(@PathVariable String id) {
        Show show = showService.getShowById(id);
        return ResponseEntity.ok(show);
    }

    @GetMapping ("/getAll")
    public ResponseEntity<List<Show>> getAllShows() {
        List<Show> shows = showService.getAllShows();
        return ResponseEntity.ok(shows);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShow(@PathVariable String id) {
        showService.deleteShow(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/shows/getByMovieId/{movieId}")
    public ResponseEntity<List<Show>> getShowByMovieId(@PathVariable String movieId) {
        List<Show> shows = showService.findByMovieId(movieId);
        return ResponseEntity.ok(shows);
    }
}
