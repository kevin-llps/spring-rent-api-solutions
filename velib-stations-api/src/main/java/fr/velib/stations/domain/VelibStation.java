package fr.velib.stations.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "velib_station")
public class VelibStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;

    @Column(name = "stationcode")
    private String stationCode;

    @Column(name = "name")
    private String name;

    @Column(name = "is_installed")
    private String isInstalled;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "num_docks_available")
    private int numDocksAvailable;

    @Column(name = "num_bikes_available")
    private int numBikesAvailable;

    @Column(name = "mechanical")
    private int mechanical;

    @Column(name = "ebike")
    private int eBike;

    @Column(name = "is_renting")
    private String isRenting;

    @Column(name = "is_returning")
    private String isReturning;

    @Column(name = "due_date")
    private String dueDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private GeoCoordinates geoCoordinates;

    @Column(name = "town_name")
    private String townName;

    @Column(name = "code_insee_town")
    private String codeInseeTown;

    @Column(name = "station_opening_hours")
    private String stationOpeningHours;

}