package fr.esgi.rent.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.esgi.rent.client.dto.VelibStationDto;
import fr.esgi.rent.client.dto.VelibStationsRequestDto;
import fr.esgi.rent.client.dto.VelibStationsResponseDto;
import fr.esgi.rent.exception.VelibStationClientException;
import org.junit.jupiter.api.Test;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class VelibStationClientTest {

    @Test
    void shouldReturnTownsWithVelibStations() throws Exception {
        HttpClient httpClient = mock(HttpClient.class);
        VelibStationConfigurationProperties conf = mock(VelibStationConfigurationProperties.class);
        ObjectMapper objectMapper = new ObjectMapper();

        when(conf.getHost()).thenReturn("localhost");
        when(conf.getPort()).thenReturn(8081);
        when(conf.getPath()).thenReturn("/stations/velibs");

        VelibStationDto station1 = new VelibStationDto(
                "41303", "Gare", "OUI", 0, 0, 31, 8, 23, "OUI", "OUI", "2025-04-23T12:58:03+00:00",
                null, "Nogent-sur-Marne", "94052", null
        );
        VelibStationsResponseDto responseDto = new VelibStationsResponseDto(List.of(station1));
        String responseJson = objectMapper.writeValueAsString(responseDto);

        HttpResponse<String> httpResponse = mock(HttpResponse.class);
        when(httpResponse.statusCode()).thenReturn(200);
        when(httpResponse.body()).thenReturn(responseJson);

        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(httpResponse);

        VelibStationClient client = new VelibStationClient(httpClient, conf, objectMapper);

        Set<String> result = client.getTownsWithVelibStations(List.of("Nogent-sur-Marne", "Vincennes"));

        assertThat(result).containsExactly("Nogent-sur-Marne");
    }

    @Test
    void shouldThrowExceptionOnNon200Status() throws Exception {
        HttpClient httpClient = mock(HttpClient.class);
        VelibStationConfigurationProperties conf = mock(VelibStationConfigurationProperties.class);
        ObjectMapper objectMapper = new ObjectMapper();

        when(conf.getHost()).thenReturn("localhost");
        when(conf.getPort()).thenReturn(8081);
        when(conf.getPath()).thenReturn("/stations/velibs");

        HttpResponse<String> httpResponse = mock(HttpResponse.class);
        when(httpResponse.statusCode()).thenReturn(500);
        when(httpResponse.body()).thenReturn("Erreur serveur");

        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(httpResponse);

        VelibStationClient client = new VelibStationClient(httpClient, conf, objectMapper);

        assertThatThrownBy(() -> client.getTownsWithVelibStations(List.of("Paris")))
                .isInstanceOf(VelibStationClientException.class)
                .hasMessageContaining("Erreur 500");
    }

    @Test
    void shouldThrowExceptionOnIOException() throws Exception {
        HttpClient httpClient = mock(HttpClient.class);
        VelibStationConfigurationProperties conf = mock(VelibStationConfigurationProperties.class);
        ObjectMapper objectMapper = new ObjectMapper();

        when(conf.getHost()).thenReturn("localhost");
        when(conf.getPort()).thenReturn(8081);
        when(conf.getPath()).thenReturn("/stations/velibs");

        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenThrow(new java.io.IOException("IO error"));

        VelibStationClient client = new VelibStationClient(httpClient, conf, objectMapper);

        assertThatThrownBy(() -> client.getTownsWithVelibStations(List.of("Paris")))
                .isInstanceOf(VelibStationClientException.class)
                .hasMessageContaining("Erreur lors de l'appel à l'API Velib Stations");
    }
}
