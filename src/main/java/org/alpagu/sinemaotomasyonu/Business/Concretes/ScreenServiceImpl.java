package org.alpagu.sinemaotomasyonu.Business.Concretes;

import org.alpagu.sinemaotomasyonu.Business.Abstracts.ScreenService;
import org.alpagu.sinemaotomasyonu.DataAccess.Abstracts.ScreenRepository;
import org.alpagu.sinemaotomasyonu.Entities.Concretes.Screen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreenServiceImpl implements ScreenService {

    private final ScreenRepository screenRepository;

    @Autowired
    public ScreenServiceImpl(ScreenRepository screenRepository) {
        this.screenRepository = screenRepository;
    }

    @Override
    public Screen saveScreen(Screen screen) {
        return screenRepository.save(screen);
    }

    @Override
    public Screen updateScreen(String screenId, Screen screen) {
        Screen existingScreen = screenRepository.findById(screenId)
                .orElseThrow(() -> new RuntimeException("Screen not found"));
        existingScreen.setNoOfSeatsGold(screen.getNoOfSeatsGold());
        existingScreen.setNoOfSeatsSilver(screen.getNoOfSeatsSilver());
        existingScreen.setTheatre(screen.getTheatre());
        return screenRepository.save(existingScreen);
    }

    @Override
    public Screen getScreenById(String screenId) {
        return screenRepository.findById(screenId)
                .orElseThrow(() -> new RuntimeException("Screen not found"));
    }

    @Override
    public List<Screen> getAllScreens() {
        return screenRepository.findAll();
    }

    @Override
    public void deleteScreen(String screenId) {
        screenRepository.deleteById(screenId);
    }
}
