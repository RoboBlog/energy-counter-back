package pl.energycoutner.other;

import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import org.springframework.stereotype.Component;
import pl.energycoutner.model.EnergyMeter;
import pl.energycoutner.model.Measurement;
import pl.energycoutner.model.TariffGroup;
import pl.energycoutner.model.repository.EnergyMeterRepository;
import pl.energycoutner.model.repository.MeasurementRepository;
import pl.energycoutner.model.repository.TariffGroupRepository;
import pl.energycoutner.service.CostCalculator;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.YearMonth;

@Component
public class RunAtStart {

    private final CostCalculator costCalculator;
    private final TariffGroupRepository tariffGroupRepository;
    private final MeasurementRepository measurementRepository;
    private final EnergyMeterRepository energyMeterRepository;

    public RunAtStart(CostCalculator costCalculator, TariffGroupRepository tariffGroupRepository, MeasurementRepository measurementRepository, EnergyMeterRepository energyMeterRepository) {
        this.costCalculator = costCalculator;
        this.tariffGroupRepository = tariffGroupRepository;
        this.measurementRepository = measurementRepository;
        this.energyMeterRepository = energyMeterRepository;
    }

    @PostConstruct
    public void init() throws UnparsableExpressionException, UnknownFunctionException {
        TariffGroup tariffGroupA = new TariffGroup("A", new BigDecimal("234"),
                "(0.3 * powerConsumption + 50) * price");
        TariffGroup tariffGroupB = new TariffGroup("B", new BigDecimal("234"),
                "(powerConsumption+5)*0,7 * price");
        TariffGroup tariffGroupC = new TariffGroup("C", new BigDecimal("234"),
                "powerConsumption*2 / 2,3 * price");
        TariffGroup tariffGroupD = new TariffGroup("D", new BigDecimal("234"),
                "powerConsumption+50 - powerConsumption / 1,65 * price");
        TariffGroup tariffGroupE = new TariffGroup("E", new BigDecimal("234"),
                "0,8*powerConsumption+( powerConsumption / 4) * price");
        tariffGroupRepository.save(tariffGroupA);
        tariffGroupRepository.save(tariffGroupB);
        tariffGroupRepository.save(tariffGroupC);
        tariffGroupRepository.save(tariffGroupD);
        tariffGroupRepository.save(tariffGroupE);

        EnergyMeter energyMeter1 = new EnergyMeter("test1");
        EnergyMeter energyMeter2 = new EnergyMeter("test2");
        EnergyMeter energyMeter3 = new EnergyMeter("test3");
        EnergyMeter energyMeter4 = new EnergyMeter("test4");
        energyMeterRepository.save(energyMeter1);
        energyMeterRepository.save(energyMeter2);
        energyMeterRepository.save(energyMeter3);
        energyMeterRepository.save(energyMeter4);


        Measurement measurement1 = new Measurement(energyMeter1, YearMonth.parse("2015-12"),
                costCalculator.calculateCost(tariffGroupA, new BigDecimal("14")), new BigDecimal("14"), tariffGroupA);
        Measurement measurement2 = new Measurement(energyMeter2, YearMonth.parse("2017-12"),
                costCalculator.calculateCost(tariffGroupB, new BigDecimal("75")), new BigDecimal("75"), tariffGroupB);
        Measurement measurement3 = new Measurement(energyMeter3, YearMonth.parse("2017-12"),
                costCalculator.calculateCost(tariffGroupC, new BigDecimal("123")), new BigDecimal("123"), tariffGroupC);
        Measurement measurement4 = new Measurement(energyMeter4, YearMonth.parse("2017-11"),
                costCalculator.calculateCost(tariffGroupD, new BigDecimal("441")), new BigDecimal("41"), tariffGroupD);
        measurementRepository.save(measurement1);
        measurementRepository.save(measurement2);
        measurementRepository.save(measurement3);
        measurementRepository.save(measurement4);
    }

}
