package com.epam.brest.courses.service;

import com.epam.brest.courses.model.dto.CarDto;

import java.util.List;

/**
 * CarDto DAO Interface.
 */
public interface CarDtoService {

    /**
     * Get all cars with count rent by car.
     *
     * @return cars list.
     */
    List<CarDto> findAllCountRent();

}
