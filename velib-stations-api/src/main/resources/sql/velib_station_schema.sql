-- Table geo_coordinates
CREATE TABLE geo_coordinates (
    id BINARY(16) PRIMARY KEY NOT NULL,
    lon DOUBLE NOT NULL,
    lat DOUBLE NOT NULL
);

-- Table velib_station
CREATE TABLE velib_station (
    id BINARY(16) PRIMARY KEY NOT NULL,
    stationcode VARCHAR(50) NOT NULL,
    name VARCHAR(100) NOT NULL,
    is_installed VARCHAR(10) NOT NULL,
    capacity INT NOT NULL,
    num_docks_available INT NOT NULL,
    num_bikes_available INT NOT NULL,
    mechanical INT NOT NULL,
    ebike INT NOT NULL,
    is_renting VARCHAR(10) NOT NULL,
    is_returning VARCHAR(10) NOT NULL,
    due_date VARCHAR(50) NOT NULL,
    geo_coordinates_id BINARY(16) NOT NULL,
    town_name VARCHAR(100) NOT NULL,
    code_insee_town VARCHAR(20) NOT NULL,
    station_opening_hours VARCHAR(100),
    CONSTRAINT fk_velib_station_geo_coordinates FOREIGN KEY (geo_coordinates_id)
        REFERENCES geo_coordinates(id)
        ON DELETE RESTRICT
        ON UPDATE RESTRICT
);
