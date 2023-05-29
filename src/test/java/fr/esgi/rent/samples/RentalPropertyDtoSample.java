package fr.esgi.rent.samples;

import fr.esgi.rent.dto.response.RentalPropertyResponseDto;

import java.util.List;

public class RentalPropertyDtoSample {

    public static List<RentalPropertyResponseDto> rentalPropertyResponseList() {
        RentalPropertyResponseDto rentalProperty = oneRentalPropertyResponse();

        return List.of(rentalProperty);
    }

    public static RentalPropertyResponseDto oneRentalPropertyResponse() {
        return RentalPropertyResponseDto.builder()
                .description("Appartement spacieux avec vue sur l'ESGI")
                .town("Paris")
                .address("77 Rue des roses")
                .propertyType("Appartement")
                .rentAmount(750.90)
                .securityDepositAmount(1200.90)
                .area(37.48)
                .build();
    }

}
