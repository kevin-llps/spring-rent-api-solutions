package fr.esgi.rent.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.esgi.rent.client.dto.VelibStationDto;
import fr.esgi.rent.client.dto.VelibStationsRequestDto;
import fr.esgi.rent.client.dto.VelibStationsResponseDto;
import fr.esgi.rent.exception.VelibStationClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class VelibStationClient {
    private final HttpClient httpClient;
    private final VelibStationConfigurationProperties velibStationConfigurationProperties;
    private final ObjectMapper objectMapper;

    public VelibStationClient(HttpClient httpClient,
                              VelibStationConfigurationProperties velibStationConfigurationProperties,
                              ObjectMapper objectMapper) {
        this.httpClient = httpClient;
        this.velibStationConfigurationProperties = velibStationConfigurationProperties;
        this.objectMapper = objectMapper;
    }

    public Set<String> getTownsWithVelibStations(List<String> towns) {
        try {
            String url = buildUrl();

            VelibStationsRequestDto velibStationsRequestDto = new VelibStationsRequestDto(towns);

            HttpRequest request = buildHttpRequest(url, velibStationsRequestDto);

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != HttpStatus.OK.value()) {
                String errorMessage = String.format("Erreur %s lors de l'appel à l'API Velib Stations : %s", response.statusCode(), response.body());

                log.error(errorMessage);

                throw new VelibStationClientException(errorMessage);
            }

            VelibStationsResponseDto velibStationsResponse = objectMapper.readValue(response.body(), VelibStationsResponseDto.class);

            return velibStationsResponse.stations().stream()
                    .map(VelibStationDto::nomCommune)
                    .collect(Collectors.toSet());

        } catch (IOException | InterruptedException e) {
            String errorMessage = "Erreur lors de l'appel à l'API Velib Stations";

            log.error(errorMessage, e);

            throw new VelibStationClientException(errorMessage, e);
        }
    }

    private String buildUrl() {
        return String.format("http://%s:%s%s",
                velibStationConfigurationProperties.getHost(),
                velibStationConfigurationProperties.getPort(),
                velibStationConfigurationProperties.getPath());
    }

    private HttpRequest buildHttpRequest(String url, VelibStationsRequestDto velibStationsRequestDto) throws JsonProcessingException {
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .method(HttpMethod.GET.name(), HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(velibStationsRequestDto)))
                .build();
    }

}
