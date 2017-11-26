package pl.energycoutner.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.energycoutner.model.EnergyMeter;

import java.util.List;

@Repository
public interface EnergyMeterRepository extends CrudRepository<EnergyMeter, Long> {
    List<EnergyMeter> findAll();
    EnergyMeter findById(Long id);
}
