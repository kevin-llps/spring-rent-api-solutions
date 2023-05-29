package fr.esgi.rent.mapper;

import fr.esgi.rent.domain.RentalPropertyEntity;
import fr.esgi.rent.dto.response.RentalPropertyResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RentalPropertyDtoMapper {

    public List<RentalPropertyResponseDto> mapToDtoList(List<RentalPropertyEntity> rentalProperties) {
        return rentalProperties.stream()
                .map(this::mapToDto)
                .toList();
    }

    public RentalPropertyResponseDto mapToDto(RentalPropertyEntity rentalProperty) {
        return new RentalPropertyResponseDto(
                rentalProperty.getDescription(),
                rentalProperty.getAddress(),
                rentalProperty.getTown(),
                rentalProperty.getPropertyType().getDesignation(),
                rentalProperty.getRentAmount(),
                rentalProperty.getSecurityDepositAmount(),
                rentalProperty.getArea());
    }

}
