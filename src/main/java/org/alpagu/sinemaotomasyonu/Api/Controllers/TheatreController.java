package org.alpagu.sinemaotomasyonu.Api.Controllers;

import org.alpagu.sinemaotomasyonu.Business.Abstracts.TheatreService;
import org.alpagu.sinemaotomasyonu.Entities.Concretes.Theatre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theatres")
public class TheatreController {

    private final TheatreService theatreService;

    @Autowired
    public TheatreController(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    @PostMapping
    public ResponseEntity<Theatre> createTheatre(@RequestBody Theatre theatre) {
        Theatre savedTheatre = theatreService.saveTheatre(theatre);
        return new ResponseEntity<>(savedTheatre, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Theatre> updateTheatre(@PathVariable String id, @RequestBody Theatre theatre) {
        Theatre updatedTheatre = theatreService.updateTheatre(id, theatre);
        return ResponseEntity.ok(updatedTheatre);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Theatre> getTheatreById(@PathVariable String id) {
        Theatre theatre = theatreService.getTheatreById(id);
        return ResponseEntity.ok(theatre);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Theatre>> getAllTheatres() {
        List<Theatre> theatres = theatreService.getAllTheatres();
        return ResponseEntity.ok(theatres);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTheatre(@PathVariable String id) {
        theatreService.deleteTheatre(id);
        return ResponseEntity.noContent().build();
    }
}
