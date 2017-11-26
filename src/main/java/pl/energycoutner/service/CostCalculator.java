package pl.energycoutner.service;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import org.springframework.stereotype.Service;
import pl.energycoutner.model.Measurement;
import pl.energycoutner.model.TariffGroup;
import pl.energycoutner.model.repository.MeasurementRepository;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;

@Service
public class CostCalculator {

    private final MeasurementRepository measurementRepository;

    public CostCalculator(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    public BigDecimal getEnergyMeterCostByDate(YearMonth date, Long energyMeterId) {
        List<Measurement> all = measurementRepository.findAllByDateAndEnergyMeter_Id(date, energyMeterId);
        return all.parallelStream().map(Measurement::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTariffCostByDate(YearMonth date, String tariffGroupCode) {
        List<Measurement> all = measurementRepository.findAllByDateAndTariffGroup_Code(date, tariffGroupCode);
        return all.parallelStream().map(Measurement::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalCost() {
        List<Measurement> all = measurementRepository.findAll();
        return all.parallelStream().map(Measurement::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalCostByMonth(YearMonth date) {
        List<Measurement> all = measurementRepository.findAllByDate(date);
        return all.parallelStream().map(Measurement::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calculateCost(TariffGroup tariffGroup, BigDecimal powerConsumption) throws UnparsableExpressionException, UnknownFunctionException {
        String expression = tariffGroup.getCostCalculationExpressionMethod().replace(",", ".");
        Calculable calc = new ExpressionBuilder(expression)
                .withVariable("powerConsumption", powerConsumption.doubleValue())
                .withVariable("price", tariffGroup.getPrice().doubleValue())
                .build();

        return BigDecimal.valueOf(calc.calculate());
    }
}
