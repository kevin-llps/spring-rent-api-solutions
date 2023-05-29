package fr.esgi.rent.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "energy_classification")
public class EnergyClassificationEntity {

    public EnergyClassificationEntity() {

    }

    public EnergyClassificationEntity(String designation) {
        this.designation = designation;
    }

    public EnergyClassificationEntity(UUID id, String designation) {
        this.id = id;
        this.designation = designation;
    }

    @GeneratedValue
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "designation")
    private String designation;
}
