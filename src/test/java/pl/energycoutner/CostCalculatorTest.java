package pl.energycoutner;

import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import org.junit.Test;
import pl.energycoutner.model.EnergyMeter;
import pl.energycoutner.model.Measurement;
import pl.energycoutner.model.TariffGroup;
import pl.energycoutner.model.repository.MeasurementRepository;
import pl.energycoutner.service.CostCalculator;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CostCalculatorTest {

    @Test
    public void calculateTest_CorrectExpression_Cost() throws UnknownFunctionException, UnparsableExpressionException {
        MeasurementRepository measurementRepository = mock(MeasurementRepository.class);

        CostCalculator costCalculator = new CostCalculator(measurementRepository);

        TariffGroup tariffGroup = new TariffGroup("A", new BigDecimal("10"),
                "(0.3 * powerConsumption + 50) * price");

        BigDecimal cost = costCalculator.calculateCost(tariffGroup, new BigDecimal("1"));

        assertThat(cost).isEqualTo("503.0");
    }

    @Test(expected = UnparsableExpressionException.class)
    public void calculateTest_IncorrectExpression_UnparsableExpressionException() throws UnknownFunctionException, UnparsableExpressionException {
        MeasurementRepository measurementRepository = mock(MeasurementRepository.class);
        CostCalculator costCalculator = new CostCalculator(measurementRepository);

        TariffGroup tariffGroup = new TariffGroup("A", new BigDecimal("10"),
                "(0.3 * powerConsumption + 50 * price");

        BigDecimal cost = costCalculator.calculateCost(tariffGroup, new BigDecimal("1"));
    }

    @Test
    public void getTotalCost_CorrectData_TotalCost() {
        MeasurementRepository measurementRepository = mock(MeasurementRepository.class);
        TariffGroup tariffGroupA = new TariffGroup("A", new BigDecimal("234"),
                "(0.3 * powerConsumption + 50) * price");

        EnergyMeter energyMeter1 = new EnergyMeter("test1");

        Measurement measurement1 = new Measurement(energyMeter1, YearMonth.parse("2015-12"), new BigDecimal("100"),
                new BigDecimal("14"), tariffGroupA);

        LinkedList<Measurement> measurements = new LinkedList<>();
        measurements.add(measurement1);
        when(measurementRepository.findAll()).thenReturn(measurements);

        CostCalculator costCalculator = new CostCalculator(measurementRepository);

        BigDecimal totalCost = costCalculator.getTotalCost();

        assertThat(totalCost).isEqualTo("100");
    }
}
