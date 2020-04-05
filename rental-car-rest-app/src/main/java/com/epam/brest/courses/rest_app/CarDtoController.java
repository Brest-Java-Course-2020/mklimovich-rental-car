package com.epam.brest.courses.rest_app;

import com.epam.brest.courses.model.dto.CarDto;
import com.epam.brest.courses.service.CarDtoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Car DTO REST controller.
 */
@RestController
public class CarDtoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarDtoController.class);

    private final CarDtoService carDtoService;

    public CarDtoController(CarDtoService carDtoService) {
        this.carDtoService = carDtoService;
    }

    /**
     * Get car Dtos.
     *
     * @return Car Dtos collection.
     */
    @GetMapping(value = "/car_dtos")
    public final Collection<CarDto> carDtos() {

        LOGGER.debug("carDtos()");
        return carDtoService.findAllCountRent();
    }
}
