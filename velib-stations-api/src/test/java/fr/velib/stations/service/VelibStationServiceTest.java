package fr.velib.stations.service;

import fr.velib.stations.domain.GeoCoordinates;
import fr.velib.stations.domain.VelibStation;
import fr.velib.stations.dto.GeoCoordinatesDto;
import fr.velib.stations.dto.VelibStationDto;
import fr.velib.stations.dto.VelibStationsResponseDto;
import fr.velib.stations.mapper.VelibStationMapper;
import fr.velib.stations.repository.VelibStationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class VelibStationServiceTest {

    @Test
    void shouldReturnStationsByTowns() {
        VelibStationRepository repo = mock(VelibStationRepository.class);
        VelibStationMapper mapper = new VelibStationMapper();

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

        when(repo.findAllByTownNameIn(List.of("Paris"))).thenReturn(List.of(station));

        VelibStationService service = new VelibStationService(repo, mapper);

        VelibStationsResponseDto response = service.findStationsByTowns(List.of("Paris"));

        assertThat(response.stations()).hasSize(1);
        VelibStationDto dto = response.stations().get(0);
        assertThat(dto.stationCode()).isEqualTo("12345");
        assertThat(dto.name()).isEqualTo("Test Station");
        assertThat(dto.coordonneesGeo()).isEqualTo(new GeoCoordinatesDto(2.5, 48.8));

        verify(repo).findAllByTownNameIn(List.of("Paris"));
        verifyNoMoreInteractions(repo);
    }
}
