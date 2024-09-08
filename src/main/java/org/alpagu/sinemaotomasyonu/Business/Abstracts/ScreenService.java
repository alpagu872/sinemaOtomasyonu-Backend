package org.alpagu.sinemaotomasyonu.Business.Abstracts;


import org.alpagu.sinemaotomasyonu.Entities.Concretes.Screen;

import java.util.List;

public interface ScreenService {
    Screen saveScreen(Screen screen);
    Screen updateScreen(String screenId, Screen screen);
    Screen getScreenById(String screenId);
    List<Screen> getAllScreens();
    void deleteScreen(String screenId);
}
