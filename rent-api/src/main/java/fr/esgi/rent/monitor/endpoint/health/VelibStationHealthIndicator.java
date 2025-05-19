package fr.esgi.rent.monitor.endpoint.health;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.esgi.rent.client.VelibStationConfigurationProperties;
import fr.esgi.rent.monitor.endpoint.health.dto.VelibStationHealthDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
@Component
public class VelibStationHealthIndicator implements HealthIndicator {

    private static final String ACTUATOR_HEALTH_ENDPOINT_PATH = "/actuator/health";

    private final HttpClient httpClient;
    private final VelibStationConfigurationProperties velibStationConfigurationProperties;
    private final ObjectMapper objectMapper;

    public VelibStationHealthIndicator(HttpClient httpClient,
                                       VelibStationConfigurationProperties velibStationConfigurationProperties,
                                       ObjectMapper objectMapper) {
        this.httpClient = httpClient;
        this.velibStationConfigurationProperties = velibStationConfigurationProperties;
        this.objectMapper = objectMapper;
    }

    @Override
    public Health health() {

        HttpRequest httpRequest = buildHttpRequest(buildUrl());

        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != HttpStatus.OK.value()) {
                String errorMessage = String.format("Erreur %s lors de l'appel à Velib Stations Health : %s", response.statusCode(), response.body());

                log.error(errorMessage);

                return Health.down().withDetail("message", errorMessage).build();
            }

            VelibStationHealthDto velibStationHealthDto = objectMapper.readValue(response.body(), VelibStationHealthDto.class);

            log.info("Velib Station Health Status : {}", velibStationHealthDto.status());

            return Health.status(velibStationHealthDto.status()).build();

        } catch (IOException | InterruptedException e) {
            String errorMessage = "Erreur lors de l'appel à Velib Stations Health";

            log.error(errorMessage, e);

            return Health.down().withDetail("message", errorMessage).build();
        }
    }

    private String buildUrl() {
        return String.format("http://%s:%s%s",
                velibStationConfigurationProperties.getHost(),
                velibStationConfigurationProperties.getPort(),
                ACTUATOR_HEALTH_ENDPOINT_PATH);
    }

    private HttpRequest buildHttpRequest(String url) {
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .GET()
                .build();
    }

}
