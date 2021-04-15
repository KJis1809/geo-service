package ru.netology.i18n;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.USA;

class LocalizationServiceImplTest {

    private final LocalizationService localizationService = new LocalizationServiceImpl();

    @Test
    void itShouldReturnRussianGreetingIfCountryIsRussia() {
        var expected = "Добро пожаловать";

        var actual = localizationService.locale(RUSSIA);

        assertEquals(expected, actual);
    }

    @Test
    void itShouldReturnEnglishGreetingIfCountryIsNonRussia() {
        var expected = "Welcome";

        var actual = localizationService.locale(USA);

        assertEquals(expected, actual);
    }
}