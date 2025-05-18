package fr.velib.stations.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GeoCoordinatesDto(
        @JsonProperty("lon") double lon,
        @JsonProperty("lat") double lat) {
}
