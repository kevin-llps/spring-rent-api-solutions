package fr.esgi.rent.repository;

import fr.esgi.rent.domain.RentalPropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RentalPropertyRepository extends JpaRepository<RentalPropertyEntity, UUID> {

}
