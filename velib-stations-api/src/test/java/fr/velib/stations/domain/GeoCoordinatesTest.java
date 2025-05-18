package fr.velib.stations.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GeoCoordinatesTest {

    @Test
    void shouldSetAndGetFields() {
        GeoCoordinates geo = new GeoCoordinates();
        geo.setLon(2.5);
        geo.setLat(48.8);

        assertThat(geo.getLon()).isEqualTo(2.5);
        assertThat(geo.getLat()).isEqualTo(48.8);
    }

    @Test
    void shouldConstructWithAllArgs() {
        GeoCoordinates geo = new GeoCoordinates(null, 2.5, 48.8);
        assertThat(geo.getLon()).isEqualTo(2.5);
        assertThat(geo.getLat()).isEqualTo(48.8);
    }
}
