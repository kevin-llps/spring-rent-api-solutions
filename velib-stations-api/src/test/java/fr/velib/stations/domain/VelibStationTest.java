package fr.velib.stations.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class VelibStationTest {

    @Test
    void shouldSetAndGetFields() {
        GeoCoordinates geo = new GeoCoordinates(null, 2.5, 48.8);
        VelibStation station = new VelibStation();
        station.setStationCode("12345");
        station.setName("Test Station");
        station.setIsInstalled("OUI");
        station.setCapacity(10);
        station.setNumDocksAvailable(5);
        station.setNumBikesAvailable(5);
        station.setMechanical(3);
        station.setEBike(2);
        station.setIsRenting("OUI");
        station.setIsReturning("OUI");
        station.setDueDate("2025-01-01T12:00:00+00:00");
        station.setGeoCoordinates(geo);
        station.setTownName("Paris");
        station.setCodeInseeTown("75056");
        station.setStationOpeningHours("08:00-20:00");

        assertThat(station.getStationCode()).isEqualTo("12345");
        assertThat(station.getName()).isEqualTo("Test Station");
        assertThat(station.getIsInstalled()).isEqualTo("OUI");
        assertThat(station.getCapacity()).isEqualTo(10);
        assertThat(station.getNumDocksAvailable()).isEqualTo(5);
        assertThat(station.getNumBikesAvailable()).isEqualTo(5);
        assertThat(station.getMechanical()).isEqualTo(3);
        assertThat(station.getEBike()).isEqualTo(2);
        assertThat(station.getIsRenting()).isEqualTo("OUI");
        assertThat(station.getIsReturning()).isEqualTo("OUI");
        assertThat(station.getDueDate()).isEqualTo("2025-01-01T12:00:00+00:00");
        assertThat(station.getGeoCoordinates()).isEqualTo(geo);
        assertThat(station.getTownName()).isEqualTo("Paris");
        assertThat(station.getCodeInseeTown()).isEqualTo("75056");
        assertThat(station.getStationOpeningHours()).isEqualTo("08:00-20:00");
    }
}
