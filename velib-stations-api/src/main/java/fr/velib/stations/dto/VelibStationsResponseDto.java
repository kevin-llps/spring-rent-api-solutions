package fr.velib.stations.dto;

import java.util.List;

public record VelibStationsResponseDto(List<VelibStationDto> stations) {
}
