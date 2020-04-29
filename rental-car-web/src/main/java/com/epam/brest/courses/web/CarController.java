package com.epam.brest.courses.web;

import com.epam.brest.courses.service.CarDtoService;
import com.epam.brest.courses.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Car controller.
 */

@Controller
public class CarController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarController.class);

    private final CarDtoService carDtoService;

    private final CarService carSrvice;


    @Autowired
    public CarController(CarDtoService carDtoService, CarService carSrvice) {
        this.carDtoService = carDtoService;
        this.carSrvice = carSrvice;
    }

/**
 * Goto cars list page.
 *
 * @return view name
*/

    public final String cars(Model model){
        LOGGER.debug("cars()");
        model.addAttribute("cars",carDtoService.findAllCountRent());
        return "cars";
    }

}
