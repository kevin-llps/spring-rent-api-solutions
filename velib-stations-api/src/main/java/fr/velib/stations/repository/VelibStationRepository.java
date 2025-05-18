package fr.velib.stations.repository;

import fr.velib.stations.domain.VelibStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VelibStationRepository extends JpaRepository<VelibStation, Long> {

    List<VelibStation> findAllByTownNameIn(List<String> townNames);

}