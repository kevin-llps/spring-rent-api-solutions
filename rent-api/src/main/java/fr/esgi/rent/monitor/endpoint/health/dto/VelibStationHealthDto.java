package fr.esgi.rent.monitor.endpoint.health.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VelibStationHealthDto(String status) {
}
