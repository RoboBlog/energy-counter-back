package pl.energycoutner.controller;

import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.energycoutner.model.Measurement;
import pl.energycoutner.service.MeasurementService;

import java.time.YearMonth;
import java.util.List;


@RequestMapping("/measurement/")
@RestController
public class MeasurementController {

    private final MeasurementService measurementService;

    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @GetMapping("/get/all")
    public List<Measurement> getAllMeasurements() {
        return measurementService.findAll();
    }

    @RequestMapping("/create")
    public Measurement createNewMeasurement(@RequestParam Long energyMeterId, @RequestParam YearMonth localDate,
                                            @RequestParam String powerConsumption, @RequestParam String tariffGroupCode) throws UnknownFunctionException, UnparsableExpressionException {
        return measurementService.createMeasurement(energyMeterId, localDate, powerConsumption, tariffGroupCode);
    }


}
