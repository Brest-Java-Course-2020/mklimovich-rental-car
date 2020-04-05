package com.epam.brest.courses.rest_app;

import com.epam.brest.courses.model.Rent;
import com.epam.brest.courses.rest_app.exception.ErrorResponse;
import com.epam.brest.courses.service.RentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

/**
 * Rent controller.
 */
@RestController
public class RentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RentController.class);
    public static final String RENT_NOT_FOUND = "rent.not_found";

    private final RentService rentService;

    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    /**
     * Goto rents list page.
     *
     * @return view name
     */
    @GetMapping(value = "/rents")
    public final Collection<Rent> rents(
            @RequestParam(value = "carId", required = false) Integer carId)
    {

        LOGGER.debug("rents(carId:{})", carId);
        if (carId != null) {
            return rentService.findByCarId(carId);
        }
        return rentService.findAll();
    }

    @GetMapping("/rents/{id}")
    public ResponseEntity<Rent> findById(@PathVariable Integer id) {

        LOGGER.debug("find rent by id({})", id);
        Optional<Rent> optional = rentService.findById(id);
        return optional.isPresent()
                ? new ResponseEntity<>(optional.get(), HttpStatus.OK)
                : new ResponseEntity(
                        new ErrorResponse(RENT_NOT_FOUND,
                                Arrays.asList("Rent not Found for id:" + id)),
                HttpStatus.NOT_FOUND);
    }

    @PostMapping(path = "/rents", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Integer> createRent(@RequestBody Rent rent) {

        LOGGER.debug("createRent({})", rent);
        Integer id = rentService.create(rent);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping(value = "/rents", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<Integer> updateRent(@RequestBody Rent rent) {

        LOGGER.debug("updateRent({})", rent);
        int result = rentService.update(rent);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @DeleteMapping(value = "/rents/{id}", produces = {"application/json"})
    public ResponseEntity<Integer> deleteRent(@PathVariable Integer id) {

        int result = rentService.delete(id);
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
