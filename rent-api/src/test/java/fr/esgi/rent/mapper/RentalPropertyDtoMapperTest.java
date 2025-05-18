package fr.esgi.rent.mapper;

import fr.esgi.rent.domain.RentalPropertyEntity;
import fr.esgi.rent.dto.response.RentalPropertyResponseDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static fr.esgi.rent.samples.RentalPropertyDtoSample.oneRentalPropertyResponse;
import static fr.esgi.rent.samples.RentalPropertyEntitySample.oneRentalPropertyEntity;
import static fr.esgi.rent.samples.RentalPropertyEntitySample.rentalPropertyEntities;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

class RentalPropertyDtoMapperTest {

    @Test
    void shouldMapToDto() {
        RentalPropertyEntity rentalPropertyEntity = oneRentalPropertyEntity();
        RentalPropertyResponseDto expectedRentalPropertyResponseDto = oneRentalPropertyResponse();

        RentalPropertyDtoMapper rentalPropertyDtoMapper = new RentalPropertyDtoMapper();

        RentalPropertyResponseDto rentalPropertyResponseDto = rentalPropertyDtoMapper.mapToDto(rentalPropertyEntity);

        assertThat(rentalPropertyResponseDto).isEqualTo(expectedRentalPropertyResponseDto);
    }

    @Test
    void shouldMapToDtoList() {
        List<RentalPropertyEntity> rentalPropertyEntities = rentalPropertyEntities();

        RentalPropertyDtoMapper rentalPropertyDtoMapper = new RentalPropertyDtoMapper();

        List<RentalPropertyResponseDto> rentalPropertyResponseList = rentalPropertyDtoMapper.mapToDtoList(rentalPropertyEntities);

        assertThat(rentalPropertyResponseList).isNotNull()
                .hasSize(1)
                .extracting("description", "address", "town", "propertyType", "rentAmount", "securityDepositAmount", "area")
                .containsExactlyInAnyOrder(
                        tuple("Appartement spacieux avec vue sur l'ESGI",
                                "77 Rue des roses",
                                "Paris",
                                "Appartement",
                                750.90,
                                1200.90,
                                37.48)
                );
    }

}
