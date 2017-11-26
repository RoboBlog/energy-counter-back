package pl.energycoutner.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.energycoutner.model.Measurement;

import java.time.YearMonth;
import java.util.List;


@Repository
public interface MeasurementRepository extends CrudRepository<Measurement, Long> {
    List<Measurement> findAll();

    List<Measurement> findAllByDate(YearMonth date);

    List<Measurement> findAllByDateAndEnergyMeter_Id(YearMonth date, long energyMeterId);

    List<Measurement> findAllByDateAndTariffGroup_Code(YearMonth date, String tariffGroupId);
}
