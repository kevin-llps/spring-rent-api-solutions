package fr.esgi.rent.client;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "velib.stations")
public class VelibStationConfigurationProperties {
    private String host;
    private int port;
    private String path;
}
