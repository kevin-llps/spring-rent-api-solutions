package fr.esgi.rent.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "property_type")
public class PropertyTypeEntity {

    public PropertyTypeEntity() {

    }

    public PropertyTypeEntity(String designation) {
        this.designation = designation;
    }

    public PropertyTypeEntity(UUID id, String designation) {
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
