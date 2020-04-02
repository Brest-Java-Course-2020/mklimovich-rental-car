package com.epam.brest.courses.rest_app;


import com.epam.brest.courses.model.Car;
import com.epam.brest.courses.rest_app.exception.CarNotFoundException;
import com.epam.brest.courses.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Car controller.
 */
@RestController
public class CarController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarController.class);

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    /**
     * Goto cars list page.
     *
     * @return view name
     */
    @GetMapping(value = "/cars")
    public final Collection<Car> cars() {

        LOGGER.debug("cars()");
        return carService.findAll();
    }

    @GetMapping("/cars/{id}")
    public Car findById(@PathVariable Integer id) {

        LOGGER.debug("find car by id({})", id);
        return carService.findById(id).orElseThrow(() -> new CarNotFoundException(id));
    }

    @PostMapping(path = "/cars", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Integer> createCar(@RequestBody Car car) {

        LOGGER.debug("createCar({})", car);
        Integer id = carService.create(car);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping(value = "/cars", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<Integer> updateCar(@RequestBody Car car) {

        LOGGER.debug("updateCar({})", car);
        int result = carService.update(car);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @DeleteMapping(value = "/cars/{id}", produces = {"application/json"})
    public ResponseEntity<Integer> deleteCar(@PathVariable Integer id) {

        int result = carService.delete(id);
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
