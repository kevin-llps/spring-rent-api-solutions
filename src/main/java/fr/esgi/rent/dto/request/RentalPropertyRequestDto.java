package fr.esgi.rent.dto.request;

import lombok.Builder;

import jakarta.validation.constraints.NotNull;

@Builder
public record RentalPropertyRequestDto(
        @NotNull String description,
        @NotNull String town,
        @NotNull String address,
        @NotNull String propertyType,
        @NotNull double rentAmount,
        @NotNull double securityDepositAmount,
        @NotNull double area,
        @NotNull int bedroomsCount,
        int floorNumber,
        int numberOfFloors,
        @NotNull int constructionYear,
        @NotNull String energyClassification,
        @NotNull boolean hasElevator,
        @NotNull boolean hasIntercom,
        @NotNull boolean hasBalcony,
        @NotNull boolean hasParkingSpace) {

}
