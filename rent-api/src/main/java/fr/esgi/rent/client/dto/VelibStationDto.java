package fr.esgi.rent.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.esgi.rent.client.VelibStationClient;

public record VelibStationDto(
        @JsonProperty("stationcode") String stationCode,
        @JsonProperty("name") String name,
        @JsonProperty("is_installed") String isInstalled,
        @JsonProperty("capacity") int capacity,
        @JsonProperty("numdocksavailable") int numDocksAvailable,
        @JsonProperty("numbikesavailable") int numBikesAvailable,
        @JsonProperty("mechanical") int mechanical,
        @JsonProperty("ebike") int eBike,
        @JsonProperty("is_renting") String isRenting,
        @JsonProperty("is_returning") String isReturning,
        @JsonProperty("duedate") String dueDate,
        @JsonProperty("coordonnees_geo") GeoCoordinatesDto coordonneesGeo,
        @JsonProperty("nom_commune") String nomCommune,
        @JsonProperty("code_insee_commune") String codeInseeCommune,
        @JsonProperty("station_opening_hours") String stationOpeningHours) {
}
