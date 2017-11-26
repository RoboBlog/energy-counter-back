package pl.energycoutner.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.energycoutner.service.CostCalculator;

import java.math.BigDecimal;
import java.time.YearMonth;

@RequestMapping("/calculator/")
@RestController
public class CostController {

    private final CostCalculator costCalculator;

    public CostController(CostCalculator costCalculator) {
        this.costCalculator = costCalculator;
    }

    @GetMapping("/get/cost/energy/{date}/{energyMeterId}")
    public BigDecimal getEnergyMeterCostByDate(@PathVariable YearMonth date, @PathVariable Long energyMeterId) {
        return costCalculator.getEnergyMeterCostByDate(date, energyMeterId);
    }

    @GetMapping("/get/cost/tariff/{date}/{tariffGroupCode}")
    public BigDecimal getTariffCostByDate(@PathVariable YearMonth date, @PathVariable String tariffGroupCode) {
        return costCalculator.getTariffCostByDate(date, tariffGroupCode);
    }

    @GetMapping("/get/cost/all")
    public BigDecimal getTotalCost() {
        return costCalculator.getTotalCost();
    }

    @GetMapping("/get/cost/all/{date}")
    public BigDecimal getTotalCostByMonth(@PathVariable YearMonth date) {
        return costCalculator.getTotalCostByMonth(date);
    }
}
