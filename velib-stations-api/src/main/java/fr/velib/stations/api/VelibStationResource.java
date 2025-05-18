package fr.velib.stations.api;

import fr.velib.stations.dto.VelibStationsRequestDto;
import fr.velib.stations.dto.VelibStationsResponseDto;
import fr.velib.stations.service.VelibStationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stations/velibs")
public class VelibStationResource {

    private final VelibStationService velibStationService;

    public VelibStationResource(VelibStationService velibStationService) {
        this.velibStationService = velibStationService;
    }

    @GetMapping
    public VelibStationsResponseDto getStationsByTowns(@RequestBody VelibStationsRequestDto request) {
        return velibStationService.findStationsByTowns(request.towns());
    }
}
