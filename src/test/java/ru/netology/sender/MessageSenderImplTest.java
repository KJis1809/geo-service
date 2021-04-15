package ru.netology.sender;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.USA;

public class MessageSenderImplTest {

    @ParameterizedTest
    @ValueSource(strings = {"172.123.12.19", "172.456.17.29", "172.341.22.11"})
    void itShouldSendRussianMessageIfIpBeginsWith172(String arguments) {
        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(arguments))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));

        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(RUSSIA))
                .thenReturn("Добро пожаловать");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, arguments);
        var actual = messageSender.send(headers);

        var expected = "Добро пожаловать";
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"96.123.12.19", "96.456.17.29", "96.341.22.11"})
    void itShouldSendEnglishMessageIfIpBeginsWith96(String arguments) {
        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(arguments))
                .thenReturn(new Location("New York", Country.USA, null,  0));

        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(USA))
                .thenReturn("Welcome");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, arguments);
        var actual = messageSender.send(headers);

        var expected = "Welcome";
        assertEquals(expected, actual);
    }
}
