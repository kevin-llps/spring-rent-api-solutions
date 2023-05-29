package fr.esgi.rent.api;

import fr.esgi.rent.domain.RentalPropertyEntity;
import fr.esgi.rent.dto.response.RentalPropertyResponseDto;
import fr.esgi.rent.exception.NotFoundRentalPropertyException;
import fr.esgi.rent.mapper.RentalPropertyDtoMapper;
import fr.esgi.rent.repository.RentalPropertyRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class RentalPropertyResource {

    private final RentalPropertyRepository rentalPropertyRepository;
    private final RentalPropertyDtoMapper rentalPropertyDtoMapper;

    public RentalPropertyResource(RentalPropertyRepository rentalPropertyRepository,
                                  RentalPropertyDtoMapper rentalPropertyDtoMapper) {
        this.rentalPropertyRepository = rentalPropertyRepository;
        this.rentalPropertyDtoMapper = rentalPropertyDtoMapper;
    }

    @GetMapping("/rental-properties")
    public List<RentalPropertyResponseDto> getRentalProperties() {
        List<RentalPropertyEntity> rentalProperties = rentalPropertyRepository.findAll();

        return rentalPropertyDtoMapper.mapToDtoList(rentalProperties);
    }

    @GetMapping("/rental-properties/{id}")
    public RentalPropertyResponseDto getRentalPropertyById(@PathVariable String id) {
        return rentalPropertyRepository.findById(UUID.fromString(id))
                .map(rentalPropertyDtoMapper::mapToDto)
                .orElseThrow(() -> new NotFoundRentalPropertyException("Le bien immobilier " + id + " est introuvable"));
    }

}
