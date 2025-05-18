package fr.velib.stations.mapper;

import fr.velib.stations.domain.GeoCoordinates;
import fr.velib.stations.domain.VelibStation;
import fr.velib.stations.dto.GeoCoordinatesDto;
import fr.velib.stations.dto.VelibStationDto;
import org.springframework.stereotype.Component;

@Component
public class VelibStationMapper {
    public VelibStationDto mapToDto(VelibStation station) {
        GeoCoordinates geo = station.getGeoCoordinates();
        GeoCoordinatesDto geoDto = geo != null
                ? new GeoCoordinatesDto(geo.getLon(), geo.getLat())
                : null;

        return new VelibStationDto(
                station.getStationCode(),
                station.getName(),
                station.getIsInstalled(),
                station.getCapacity(),
                station.getNumDocksAvailable(),
                station.getNumBikesAvailable(),
                station.getMechanical(),
                station.getEBike(),
                station.getIsRenting(),
                station.getIsReturning(),
                station.getDueDate(),
                geoDto,
                station.getTownName(),
                station.getCodeInseeTown(),
                station.getStationOpeningHours()
        );
    }
}
