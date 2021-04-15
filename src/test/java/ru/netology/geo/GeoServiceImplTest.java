package ru.netology.geo;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {

    public static final String LOCALHOST = "127.0.0.1";
    public static final String MOSCOW_IP = "172.0.32.11";
    public static final String NEW_YORK_IP = "96.44.183.149";

    private final GeoService geoService = new GeoServiceImpl();

    @ParameterizedTest
    @ValueSource(strings = LOCALHOST)
    void byIp_isLocalhost_test(String argument) {
        var locationExpected = new Location(null, null, null, 0);
        var locationActual = geoService.byIp(argument);

        assertEquals(locationExpected.getCity(), locationActual.getCity());
        assertEquals(locationExpected.getCountry(), locationActual.getCountry());
        assertEquals(locationExpected.getStreet(), locationActual.getStreet());
        assertEquals(locationExpected.getBuiling(), locationActual.getBuiling());
    }

    @ParameterizedTest
    @ValueSource(strings = MOSCOW_IP)
    void byIp_isMoscowIp_test(String argument) {
        var locationExpected = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        var locationActual = geoService.byIp(argument);

        assertEquals(locationExpected.getCity(), locationActual.getCity());
        assertEquals(locationExpected.getCountry(), locationActual.getCountry());
        assertEquals(locationExpected.getStreet(), locationActual.getStreet());
        assertEquals(locationExpected.getBuiling(), locationActual.getBuiling());
    }

    @ParameterizedTest
    @ValueSource(strings = NEW_YORK_IP)
    void byIp_isNewYorkIp_test(String argument) {
        var locationExpected = new Location("New York", Country.USA, " 10th Avenue", 32);
        var locationActual = geoService.byIp(argument);

        assertEquals(locationExpected.getCity(), locationActual.getCity());
        assertEquals(locationExpected.getCountry(), locationActual.getCountry());
        assertEquals(locationExpected.getStreet(), locationActual.getStreet());
        assertEquals(locationExpected.getBuiling(), locationActual.getBuiling());
    }

    @ParameterizedTest
    @ValueSource(strings = {"172.123.12.19", "172.456.17.29", "172.341.22.11"})
    void byIp_itShouldReturnRussianLocationIfIpStartsWith172_test(String argument) {
        var locationExpected = new Location("Moscow", Country.RUSSIA, null, 0);
        var locationActual = geoService.byIp(argument);

        assertEquals(locationExpected.getCity(), locationActual.getCity());
        assertEquals(locationExpected.getCountry(), locationActual.getCountry());
        assertEquals(locationExpected.getStreet(), locationActual.getStreet());
        assertEquals(locationExpected.getBuiling(), locationActual.getBuiling());
    }

    @ParameterizedTest
    @ValueSource(strings = {"96.123.12.19", "96.456.17.29", "96.341.22.11"})
    void byIp_itShouldReturnUSALocationIfIpStartsWith96_test(String argument) {
        var locationExpected = new Location("New York", Country.USA, null,  0);
        var locationActual = geoService.byIp(argument);

        assertEquals(locationExpected.getCity(), locationActual.getCity());
        assertEquals(locationExpected.getCountry(), locationActual.getCountry());
        assertEquals(locationExpected.getStreet(), locationActual.getStreet());
        assertEquals(locationExpected.getBuiling(), locationActual.getBuiling());
    }
}