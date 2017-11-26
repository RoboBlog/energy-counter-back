package pl.energycoutner.service;

import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import org.springframework.stereotype.Service;
import pl.energycoutner.model.EnergyMeter;
import pl.energycoutner.model.Measurement;
import pl.energycoutner.model.TariffGroup;
import pl.energycoutner.model.repository.EnergyMeterRepository;
import pl.energycoutner.model.repository.MeasurementRepository;
import pl.energycoutner.model.repository.TariffGroupRepository;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;

@Service
public class MeasurementService {
    private final EnergyMeterRepository energyMeterRepository;
    private final CostCalculator costCalculator;
    private final MeasurementRepository measurementRepository;
    private final TariffGroupRepository tariffGroupRepository;

    public MeasurementService(EnergyMeterRepository energyMeterRepository, CostCalculator costCalculator, MeasurementRepository measurementRepository, TariffGroupRepository tariffGroupRepository) {
        this.energyMeterRepository = energyMeterRepository;
        this.costCalculator = costCalculator;
        this.measurementRepository = measurementRepository;
        this.tariffGroupRepository = tariffGroupRepository;
    }

    public Measurement createMeasurement(Long energyMeterId, YearMonth localDate, String powerConsumption, String tariffGroupCode) throws UnparsableExpressionException, UnknownFunctionException {
        EnergyMeter energyMeter = energyMeterRepository.findById(energyMeterId);
        TariffGroup tariffGroup = tariffGroupRepository.findByCode(tariffGroupCode);
        BigDecimal price = costCalculator.calculateCost(tariffGroup, new BigDecimal(powerConsumption));
        Measurement measurement = new Measurement(energyMeter, localDate, price, new BigDecimal(powerConsumption), tariffGroup);
        measurementRepository.save(measurement);
        return measurement;
    }

    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }
}
