package fr.esgi.rent.samples;

import fr.esgi.rent.domain.EnergyClassificationEntity;
import fr.esgi.rent.domain.PropertyTypeEntity;
import fr.esgi.rent.domain.RentalPropertyEntity;

import java.util.List;
import java.util.UUID;

public class RentalPropertyEntitySample {

    public static List<RentalPropertyEntity> rentalPropertyEntities() {
        RentalPropertyEntity rentalProperty = oneRentalPropertyEntity();

        return List.of(rentalProperty);
    }

    public static RentalPropertyEntity oneRentalPropertyEntity() {
        return new RentalPropertyEntity(
                UUID.fromString("1a8ed763-928c-4155-bee9-fdbaaadc15f3"),
                "Appartement spacieux avec vue sur l'ESGI",
                "Paris",
                "77 Rue des roses",
                new PropertyTypeEntity(UUID.fromString("e58ed763-928c-4155-bee9-fdbaaadc15f3"), "Appartement"),
                750.90,
                1200.90,
                37.48,
                2,
                1,
                3,
                1990,
                new EnergyClassificationEntity(UUID.fromString("cb3ed763-928c-4155-bee9-fdbaaadc15f3"), "B"),
                false,
                false,
                true,
                false);
    }

}
