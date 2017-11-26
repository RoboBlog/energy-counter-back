package pl.energycoutner.service;

import org.springframework.stereotype.Service;
import pl.energycoutner.model.EnergyMeter;
import pl.energycoutner.model.repository.EnergyMeterRepository;

import java.util.List;

@Service
public class EnergyMeterService {

    private final EnergyMeterRepository energyMeterRepository;

    public EnergyMeterService(EnergyMeterRepository energyMeterRepository) {
        this.energyMeterRepository = energyMeterRepository;
    }

    public List<EnergyMeter> getAllEnergyMeters() {
        return energyMeterRepository.findAll();
    }

    public EnergyMeter addEnergyMeter(EnergyMeter energyMeter) {
        energyMeterRepository.save(energyMeter);
        return energyMeter;
    }

    public void deleteEnergyMeter(Long energyMeterId) {
        energyMeterRepository.delete(energyMeterId);
    }
}
