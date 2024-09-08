package org.alpagu.sinemaotomasyonu.Api.Controllers;

import org.alpagu.sinemaotomasyonu.Business.Abstracts.ScreenService;
import org.alpagu.sinemaotomasyonu.Entities.Concretes.Screen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/screens")
public class ScreenController {

    private final ScreenService screenService;

    @Autowired
    public ScreenController(ScreenService screenService) {
        this.screenService = screenService;
    }

    @PostMapping
    public ResponseEntity<Screen> createScreen(@RequestBody Screen screen) {
        Screen savedScreen = screenService.saveScreen(screen);
        return new ResponseEntity<>(savedScreen, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Screen> updateScreen(@PathVariable String id, @RequestBody Screen screen) {
        Screen updatedScreen = screenService.updateScreen(id, screen);
        return ResponseEntity.ok(updatedScreen);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Screen> getScreenById(@PathVariable String id) {
        Screen screen = screenService.getScreenById(id);
        return ResponseEntity.ok(screen);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Screen>> getAllScreens() {
        List<Screen> screens = screenService.getAllScreens();
        return ResponseEntity.ok(screens);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScreen(@PathVariable String id) {
        screenService.deleteScreen(id);
        return ResponseEntity.noContent().build();
    }
}
