package com.epam.brest.courses.web;

import com.epam.brest.courses.model.Car;
import com.epam.brest.courses.service.CarDtoService;
import com.epam.brest.courses.service.CarService;
import com.epam.brest.courses.web.validators.CarValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import javax.validation.Valid;
import java.util.Optional;

@Controller
public class CarController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarController.class);

    private final CarDtoService carDtoService;
    private final CarService carService;

    public CarController(CarDtoService carDtoService, CarService carService) {
        this.carDtoService = carDtoService;
        this.carService = carService;
    }
    @Autowired
    CarValidator carValidator;

    @GetMapping(value = "/cars")
    public final String cars(Model model) {

        LOGGER.debug("cars()");
        model.addAttribute("cars", carDtoService.findAllCountRent());
        return "cars";
    }

    /**
     * Goto edit car page.
     *
     * @return view name
     */
    @GetMapping(value = "/update/{id}")
    public final String gotoEditCarPage(@PathVariable Integer id, Model model) {

        LOGGER.debug("gotoEditCarPage({},{})", id, model);
        Optional<Car> optionalCar = carService.findById(id);
        if (optionalCar.isPresent()) {
            model.addAttribute("isNew", false);
            model.addAttribute("car", optionalCar.get());
            return "updateCar";
        } else {
            // TODO car not found - pass error message as parameter or handle not found error
            return "redirect:cars";
        }
    }
    /**
     * Update car.
     *
     * @param car car with filled data.
     * @param result binding result
     * @return view name
     */
    @PostMapping(value = "/update/{id}")
    public String updateCar(@Valid Car car, BindingResult result) {

        LOGGER.debug("updateCar({}, {})", car, result);
        carValidator.validate(car, result);
        if (result.hasErrors()) {
            return "updateCar";
        } else {
            this.carService.update(car);
            return "redirect:/cars";
        }
    }

    /**
     * Goto add car page.
     *
     * @return view name
     */
    @GetMapping(value = "/add")
    public final String gotoAddCarPage(Model model) {

        LOGGER.debug("gotoAddCarPage({})", model);
        model.addAttribute("isNew", true);
        model.addAttribute("car", new Car());
        return "addCar";
    }
    /**
     * Persist new car into persistence storage.
     *
     * @param car new car with filled data.
     * @param result     binding result.
     * @return view name
     */
    @PostMapping(value = "/add")
    public String addDepartment(@Valid Car car,
                                BindingResult result) {

        LOGGER.debug("addCar({}, {})", car, result);
        carValidator.validate(car, result);
        if (result.hasErrors()) {
            return "addCar";
        } else {
            this.carService.create(car);
            return "redirect:/cars";
        }
    }
    /**
     * Delete car.
     *
     * @return view name
     */
    @GetMapping(value = "delete/{id}")
    public final String deleteCarById(@PathVariable Integer id, Model model) {

        LOGGER.debug("delete({},{})", id, model);
        carService.delete(id);
        return "redirect:/cars";
    }

}
