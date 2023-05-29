package fr.esgi.rent.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "rental_property")
public class RentalPropertyEntity {

    public RentalPropertyEntity() {

    }

    public RentalPropertyEntity(String description,
                                String town,
                                String address,
                                PropertyTypeEntity propertyType,
                                double rentAmount,
                                double securityDepositAmount,
                                double area,
                                int numberOfBedrooms,
                                int floorNumber,
                                int numberOfFloors,
                                int constructionYear,
                                EnergyClassificationEntity energyClassification,
                                boolean hasElevator,
                                boolean hasIntercom,
                                boolean hasBalcony,
                                boolean hasParkingSpace) {
        this.description = description;
        this.town = town;
        this.address = address;
        this.propertyType = propertyType;
        this.rentAmount = rentAmount;
        this.securityDepositAmount = securityDepositAmount;
        this.area = area;
        this.numberOfBedrooms = numberOfBedrooms;
        this.floorNumber = floorNumber;
        this.numberOfFloors = numberOfFloors;
        this.constructionYear = constructionYear;
        this.energyClassification = energyClassification;
        this.hasElevator = hasElevator;
        this.hasIntercom = hasIntercom;
        this.hasBalcony = hasBalcony;
        this.hasParkingSpace = hasParkingSpace;
    }

    public RentalPropertyEntity(UUID id,
                                String description,
                                String town,
                                String address,
                                PropertyTypeEntity propertyType,
                                double rentAmount,
                                double securityDepositAmount,
                                double area,
                                int numberOfBedrooms,
                                int floorNumber,
                                int numberOfFloors,
                                int constructionYear,
                                EnergyClassificationEntity energyClassification,
                                boolean hasElevator,
                                boolean hasIntercom,
                                boolean hasBalcony,
                                boolean hasParkingSpace) {
        this.id = id;
        this.description = description;
        this.town = town;
        this.address = address;
        this.propertyType = propertyType;
        this.rentAmount = rentAmount;
        this.securityDepositAmount = securityDepositAmount;
        this.area = area;
        this.numberOfBedrooms = numberOfBedrooms;
        this.floorNumber = floorNumber;
        this.numberOfFloors = numberOfFloors;
        this.constructionYear = constructionYear;
        this.energyClassification = energyClassification;
        this.hasElevator = hasElevator;
        this.hasIntercom = hasIntercom;
        this.hasBalcony = hasBalcony;
        this.hasParkingSpace = hasParkingSpace;
    }

    @GeneratedValue
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "description")
    private String description;

    @Column(name = "town")
    private String town;

    @Column(name = "address")
    private String address;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "property_type_id")
    private PropertyTypeEntity propertyType;

    @Column(name = "rent_amount")
    private double rentAmount;

    @Column(name = "security_deposit_amount")
    private double securityDepositAmount;

    @Column(name = "area")
    private double area;

    @Column(name = "number_of_bedrooms")
    private int numberOfBedrooms;

    @Column(name = "floor_number")
    private int floorNumber;

    @Column(name = "number_of_floors")
    private int numberOfFloors;

    @Column(name = "construction_year")
    private int constructionYear;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "energy_classification_id")
    private EnergyClassificationEntity energyClassification;

    @Column(name = "has_elevator")
    private boolean hasElevator;

    @Column(name = "has_intercom")
    private boolean hasIntercom;

    @Column(name = "has_balcony")
    private boolean hasBalcony;

    @Column(name = "has_parking_space")
    private boolean hasParkingSpace;

}
