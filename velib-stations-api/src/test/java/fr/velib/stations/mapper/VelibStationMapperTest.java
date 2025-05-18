package fr.velib.stations.mapper;

import fr.velib.stations.domain.GeoCoordinates;
import fr.velib.stations.domain.VelibStation;
import fr.velib.stations.dto.GeoCoordinatesDto;
import fr.velib.stations.dto.VelibStationDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class VelibStationMapperTest {

    @Test
    void shouldMapToDto() {
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
        station.setStationOpeningHours(null);

        VelibStationMapper mapper = new VelibStationMapper();

        VelibStationDto dto = mapper.mapToDto(station);

        assertThat(dto.stationCode()).isEqualTo("12345");
        assertThat(dto.name()).isEqualTo("Test Station");
        assertThat(dto.isInstalled()).isEqualTo("OUI");
        assertThat(dto.capacity()).isEqualTo(10);
        assertThat(dto.numDocksAvailable()).isEqualTo(5);
        assertThat(dto.numBikesAvailable()).isEqualTo(5);
        assertThat(dto.mechanical()).isEqualTo(3);
        assertThat(dto.eBike()).isEqualTo(2);
        assertThat(dto.isRenting()).isEqualTo("OUI");
        assertThat(dto.isReturning()).isEqualTo("OUI");
        assertThat(dto.dueDate()).isEqualTo("2025-01-01T12:00:00+00:00");
        assertThat(dto.coordonneesGeo()).isEqualTo(new GeoCoordinatesDto(2.5, 48.8));
        assertThat(dto.nomCommune()).isEqualTo("Paris");
        assertThat(dto.codeInseeCommune()).isEqualTo("75056");
        assertThat(dto.stationOpeningHours()).isNull();
    }
}
