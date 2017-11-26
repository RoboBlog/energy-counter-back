package pl.energycoutner.controller;

import org.springframework.web.bind.annotation.*;
import pl.energycoutner.model.EnergyMeter;
import pl.energycoutner.service.EnergyMeterService;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/energy/")
@RestController
public class EnergyMeterController {

    private final EnergyMeterService energyMeterService;

    public EnergyMeterController(EnergyMeterService energyMeterService) {
        this.energyMeterService = energyMeterService;
    }

    @GetMapping("/get/all")
    public List<EnergyMeter> getAllEnergyMeters() {
        return energyMeterService.getAllEnergyMeters();
    }

    @PostMapping("/add")
    public EnergyMeter addEnergyMeter(@Valid @RequestBody EnergyMeter energyMeter) {
        return energyMeterService.addEnergyMeter(energyMeter);
    }

    @DeleteMapping("/delete/{energyMeterId}")
    public void deleteEnergyMeter(@PathVariable Long energyMeterId) {
        energyMeterService.deleteEnergyMeter(energyMeterId);
    }
}
