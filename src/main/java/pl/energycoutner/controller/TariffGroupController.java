package pl.energycoutner.controller;

import org.springframework.web.bind.annotation.*;
import pl.energycoutner.model.TariffGroup;
import pl.energycoutner.service.TariffGroupService;

import java.util.List;

@RequestMapping("/tariff/")
@RestController
public class TariffGroupController {

    private final TariffGroupService tariffGroupService;

    public TariffGroupController(TariffGroupService tariffGroupService) {
        this.tariffGroupService = tariffGroupService;
    }

    @GetMapping("/get/all")
    public List<TariffGroup> getAllTariffGroups() {
        return tariffGroupService.findAll();
    }

    @PostMapping("/set/price")
    public void setTariffGroupPrice(@RequestParam String price, @RequestParam String code) {
        tariffGroupService.setTariffGroupPrice(price, code);
    }
}


