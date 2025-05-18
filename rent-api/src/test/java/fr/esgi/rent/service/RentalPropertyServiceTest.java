package fr.esgi.rent.service;

import fr.esgi.rent.client.VelibStationClient;
import fr.esgi.rent.domain.RentalPropertyEntity;
import fr.esgi.rent.dto.request.RentalPropertySearchRequestDto;
import fr.esgi.rent.dto.response.RentalPropertyResponseDto;
import fr.esgi.rent.mapper.RentalPropertyDtoMapper;
import fr.esgi.rent.repository.RentalPropertyRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class RentalPropertyServiceTest {

    @Test
    void shouldReturnAllRentalPropertiesWhenNearVelibStationsIsNull() {
        RentalPropertyRepository repository = mock(RentalPropertyRepository.class);
        RentalPropertyDtoMapper mapper = mock(RentalPropertyDtoMapper.class);
        VelibStationClient velibStationClient = mock(VelibStationClient.class);

        RentalPropertyEntity property = new RentalPropertyEntity();
        property.setTown("Paris");
        List<RentalPropertyEntity> properties = List.of(property);

        when(repository.findAll()).thenReturn(properties);
        when(mapper.mapToDtoList(properties)).thenReturn(List.of(new RentalPropertyResponseDto(
                "desc", "address", "Paris", "Appartement", 1000.0, 1200.0, 50.0
        )));

        RentalPropertyService service = new RentalPropertyService(repository, mapper, velibStationClient);

        List<RentalPropertyResponseDto> result = service.findRentalProperties(null);

        assertThat(result).hasSize(1);
        verify(repository).findAll();
        verify(mapper).mapToDtoList(properties);
        verifyNoInteractions(velibStationClient);
    }

    @Test
    void shouldReturnRentalPropertiesNearVelibStations() {
        RentalPropertyRepository repository = mock(RentalPropertyRepository.class);
        RentalPropertyDtoMapper mapper = mock(RentalPropertyDtoMapper.class);
        VelibStationClient velibStationClient = mock(VelibStationClient.class);

        RentalPropertyEntity property1 = new RentalPropertyEntity();
        property1.setTown("Paris");
        RentalPropertyEntity property2 = new RentalPropertyEntity();
        property2.setTown("Lyon");
        List<RentalPropertyEntity> properties = List.of(property1, property2);

        when(repository.findAll()).thenReturn(properties);
        when(velibStationClient.getTownsWithVelibStations(List.of("Paris", "Lyon"))).thenReturn(Set.of("Paris"));
        when(mapper.mapToDtoList(List.of(property1))).thenReturn(List.of(new RentalPropertyResponseDto(
                "desc", "address", "Paris", "Appartement", 1000.0, 1200.0, 50.0
        )));

        RentalPropertyService service = new RentalPropertyService(repository, mapper, velibStationClient);

        RentalPropertySearchRequestDto searchRequest = new RentalPropertySearchRequestDto(true);
        List<RentalPropertyResponseDto> result = service.findRentalProperties(searchRequest);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).town()).isEqualTo("Paris");
        verify(repository).findAll();
        verify(velibStationClient).getTownsWithVelibStations(List.of("Paris", "Lyon"));
        verify(mapper).mapToDtoList(List.of(property1));
    }

    @Test
    void shouldReturnEmptyListIfNoRentalPropertiesNearVelibStations() {
        RentalPropertyRepository repository = mock(RentalPropertyRepository.class);
        RentalPropertyDtoMapper mapper = mock(RentalPropertyDtoMapper.class);
        VelibStationClient velibStationClient = mock(VelibStationClient.class);

        RentalPropertyEntity property1 = new RentalPropertyEntity();
        property1.setTown("Paris");
        List<RentalPropertyEntity> properties = List.of(property1);

        when(repository.findAll()).thenReturn(properties);
        when(velibStationClient.getTownsWithVelibStations(List.of("Paris"))).thenReturn(Set.of());
        when(mapper.mapToDtoList(List.of())).thenReturn(List.of());

        RentalPropertyService service = new RentalPropertyService(repository, mapper, velibStationClient);

        RentalPropertySearchRequestDto searchRequest = new RentalPropertySearchRequestDto(true);
        List<RentalPropertyResponseDto> result = service.findRentalProperties(searchRequest);

        assertThat(result).isEmpty();
        verify(repository).findAll();
        verify(velibStationClient).getTownsWithVelibStations(List.of("Paris"));
        verify(mapper).mapToDtoList(List.of());
    }
}
