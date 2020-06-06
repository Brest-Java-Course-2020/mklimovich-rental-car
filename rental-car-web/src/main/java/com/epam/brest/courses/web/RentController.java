package com.epam.brest.courses.web;

import com.epam.brest.courses.model.Rent;
import com.epam.brest.courses.service.CarService;
import com.epam.brest.courses.service.RentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Rent controller.
 */
@Controller
@RequestMapping("/rents/")
public class RentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RentController.class);

    private final CarService carService;

    private final RentService rentService;

    @Autowired
    public RentController(CarService carService, RentService rentService) {
        this.carService = carService;
        this.rentService = rentService;
    }

    /**
     * Goto rent page.
     *
     * @return view name
     */
    @GetMapping(value = "/")
    public final String rents(Model model) {

        LOGGER.debug("rents()");
        model.addAttribute("rents", rentService.findAll());
        return "rents";
    }
    /**
     * Goto edit rent page.
     *
     * @return view name
     */
    @GetMapping(value = "/rents/edit/{id}")
    public final String gotoEditRentPage(@PathVariable Integer id, Model model) {

        LOGGER.debug("gotoEditRentPage({},{})", id, model);
        Optional<Rent> optionalRent = rentService.findById(id);
        if (optionalRent.isPresent()) {
            model.addAttribute("isNew", false);
            model.addAttribute("rent", optionalRent.get());
            model.addAttribute("cars", carService.findAll());
            return "updateRent";
        } else {
            // TODO employee not found - pass error message as parameter or handle not found error
            return "redirect:rents";
        }
    }

    /**
     * Update rent.
     *
     * @param rent  rent with filled data.
     * @param result    binding result
     * @return          view name
     */
    @PostMapping(value = "/rents/edit/{id}")
    public String updateRent(@Valid Rent rent,
                                 BindingResult result) {

        LOGGER.debug("updateRent({}, {})", rent, result);
        // TODO implement validation
        if (result.hasErrors()) {
            return "updateRent";
        } else {
            this.rentService.update(rent);
            return "redirect:/rents";
        }
    }

}
