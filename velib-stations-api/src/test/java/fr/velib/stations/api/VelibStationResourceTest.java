package fr.velib.stations.api;

import fr.velib.stations.dto.GeoCoordinatesDto;
import fr.velib.stations.dto.VelibStationDto;
import fr.velib.stations.dto.VelibStationsResponseDto;
import fr.velib.stations.service.VelibStationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VelibStationResource.class)
class VelibStationResourceTest {

    @Value("classpath:/json/velibStationsRequest.json")
    private org.springframework.core.io.Resource velibStationsRequest;

    @Value("classpath:/json/velibStationsResponse.json")
    private org.springframework.core.io.Resource velibStationsResponse;

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private VelibStationService velibStationService;

    @Test
    void shouldReturnStationsByTowns() throws Exception {
        VelibStationDto stationDto = new VelibStationDto(
                "12345",
                "Test Station",
                "OUI",
                10,
                5,
                5,
                3,
                2,
                "OUI",
                "OUI",
                "2025-01-01T12:00:00+00:00",
                new GeoCoordinatesDto(2.5, 48.8),
                "Paris",
                "75056",
                null
        );
        VelibStationsResponseDto responseDto = new VelibStationsResponseDto(List.of(stationDto));

        when(velibStationService.findStationsByTowns(List.of("Paris"))).thenReturn(responseDto);

        mockMvc.perform(get("/stations/velibs")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(new String(velibStationsRequest.getInputStream().readAllBytes())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(content().json(new String(velibStationsResponse.getInputStream().readAllBytes())));

        verify(velibStationService).findStationsByTowns(List.of("Paris"));
        verifyNoMoreInteractions(velibStationService);
    }
}
