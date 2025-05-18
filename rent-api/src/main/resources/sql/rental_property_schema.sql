-- Table property_type
CREATE TABLE property_type (
    id BINARY(16) PRIMARY KEY NOT NULL,
    designation VARCHAR(100) NOT NULL
);

-- Table energy_classification
CREATE TABLE energy_classification (
    id BINARY(16) PRIMARY KEY NOT NULL,
    designation CHAR(1) NOT NULL
);

-- Table rental_property
CREATE TABLE rental_property (
    id BINARY(16) PRIMARY KEY NOT NULL,
    description VARCHAR(100) NOT NULL,
    town VARCHAR(100) NOT NULL,
    address VARCHAR(100) NOT NULL,
    property_type_id BINARY(16) NOT NULL,
    rent_amount DOUBLE NOT NULL,
    security_deposit_amount DOUBLE NOT NULL,
    area DOUBLE NOT NULL,
    number_of_bedrooms TINYINT NOT NULL,
    floor_number SMALLINT,
    number_of_floors SMALLINT,
    construction_year SMALLINT NOT NULL,
    energy_classification_id BINARY(16) NOT NULL,
    has_elevator BIT(1) NOT NULL,
    has_intercom BIT(1) NOT NULL,
    has_balcony BIT(1) NOT NULL,
    has_parking_space BIT(1) NOT NULL,
    CONSTRAINT fk_rental_property_property_type FOREIGN KEY (property_type_id)
        REFERENCES property_type(id)
        ON DELETE RESTRICT
        ON UPDATE RESTRICT,
    CONSTRAINT fk_rental_property_energy_classification FOREIGN KEY (energy_classification_id)
        REFERENCES energy_classification(id)
        ON DELETE RESTRICT
        ON UPDATE RESTRICT
);
