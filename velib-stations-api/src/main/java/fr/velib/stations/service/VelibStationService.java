package fr.velib.stations.service;

import fr.velib.stations.dto.VelibStationDto;
import fr.velib.stations.dto.VelibStationsResponseDto;
import fr.velib.stations.mapper.VelibStationMapper;
import fr.velib.stations.repository.VelibStationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VelibStationService {

    private final VelibStationRepository velibStationRepository;
    private final VelibStationMapper velibStationMapper;

    public VelibStationService(VelibStationRepository velibStationRepository,
                               VelibStationMapper velibStationMapper) {
        this.velibStationRepository = velibStationRepository;
        this.velibStationMapper = velibStationMapper;
    }

    public VelibStationsResponseDto findStationsByTowns(List<String> towns) {
        List<VelibStationDto> velibStations = velibStationRepository.findAllByTownNameIn(towns)
                .stream()
                .map(velibStationMapper::mapToDto)
                .toList();

        return new VelibStationsResponseDto(velibStations);
    }

}
