package com.epam.brest.courses.dao;

import com.epam.brest.courses.model.dto.CarDto;

import java.util.List;

/**
 * CarDto DAO Interface.
 */
public interface CarDtoDao {

    /**
     * Get all cars with count rent by car.
     *
     * @return cars list.
     */
    List<CarDto> findAllCountRent();

}
