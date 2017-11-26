package pl.energycoutner.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.energycoutner.model.TariffGroup;

import java.util.List;

@Repository
public interface TariffGroupRepository extends CrudRepository<TariffGroup, Long> {
    List<TariffGroup> findAll();

    TariffGroup findByCode(String code);
}

