package fr.esgi.rent.dto.request;

import lombok.Builder;

@Builder
public record RentalPropertyRequestDto(
        String description,
        String town,
        String address,
        String propertyType,
        double rentAmount,
        double securityDepositAmount,
        double area,
        int bedroomsCount,
        int floorNumber,
        int numberOfFloors,
        int constructionYear,
        String energyClassification,
        boolean hasElevator,
        boolean hasIntercom,
        boolean hasBalcony,
        boolean hasParkingSpace) {

}
