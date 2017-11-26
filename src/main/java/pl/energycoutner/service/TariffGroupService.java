package pl.energycoutner.service;

import org.springframework.stereotype.Service;
import pl.energycoutner.model.TariffGroup;
import pl.energycoutner.model.repository.TariffGroupRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TariffGroupService {

    private final TariffGroupRepository tariffGroupRepository;

    public TariffGroupService(TariffGroupRepository tariffGroupRepository) {
        this.tariffGroupRepository = tariffGroupRepository;
    }

    public List<TariffGroup> findAll() {
        return tariffGroupRepository.findAll();
    }

    public void setTariffGroupPrice(String price, String code) {
        TariffGroup tariffGroup = tariffGroupRepository.findByCode(code);

        tariffGroup.setPrice(new BigDecimal(price));
        tariffGroupRepository.save(tariffGroup);
    }
}
