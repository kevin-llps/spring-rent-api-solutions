package fr.esgi.rent.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Builder;

import jakarta.validation.constraints.NotNull;

@Builder
public record RentalPropertyRequestDto(
        @NotNull String description,
        @NotNull String town,
        @NotNull String address,
        @NotNull String propertyType,
        @Min(1) double rentAmount,
        @Min(1) double securityDepositAmount,
        @Min(1) double area,
        @Min(1) int bedroomsCount,
        int floorNumber,
        int numberOfFloors,
        @Min(1900) int constructionYear,
        @NotNull String energyClassification,
        @NotNull Boolean hasElevator,
        @NotNull Boolean hasIntercom,
        @NotNull Boolean hasBalcony,
        @NotNull Boolean hasParkingSpace) {

}
