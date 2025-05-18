package fr.esgi.rent.service;

import fr.esgi.rent.client.VelibStationClient;
import fr.esgi.rent.domain.RentalPropertyEntity;
import fr.esgi.rent.dto.request.RentalPropertySearchRequestDto;
import fr.esgi.rent.dto.response.RentalPropertyResponseDto;
import fr.esgi.rent.mapper.RentalPropertyDtoMapper;
import fr.esgi.rent.repository.RentalPropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RentalPropertyService {

    private final RentalPropertyRepository rentalPropertyRepository;
    private final RentalPropertyDtoMapper rentalPropertyDtoMapper;
    private final VelibStationClient velibStationClient;

    public RentalPropertyService(RentalPropertyRepository rentalPropertyRepository,
                                 RentalPropertyDtoMapper rentalPropertyDtoMapper,
                                 VelibStationClient velibStationClient) {
        this.rentalPropertyRepository = rentalPropertyRepository;
        this.rentalPropertyDtoMapper = rentalPropertyDtoMapper;
        this.velibStationClient = velibStationClient;
    }

    public List<RentalPropertyResponseDto> findRentalProperties(RentalPropertySearchRequestDto searchRequest) {
        List<RentalPropertyEntity> rentalPropertiesNearVelibStations = rentalPropertyRepository.findAll();

        List<String> rentalPropertyTowns = rentalPropertiesNearVelibStations.stream()
                .map(RentalPropertyEntity::getTown)
                .distinct()
                .toList();

        if (searchRequest != null && Boolean.TRUE.equals(searchRequest.nearVelibStations())) {

            Set<String> townsWithVelib = velibStationClient.getTownsWithVelibStations(rentalPropertyTowns);

            rentalPropertiesNearVelibStations = rentalPropertiesNearVelibStations.stream()
                    .filter(rentalPropertyEntity -> townsWithVelib.contains(rentalPropertyEntity.getTown()))
                    .toList();
        }

        return rentalPropertyDtoMapper.mapToDtoList(rentalPropertiesNearVelibStations);
    }

}
