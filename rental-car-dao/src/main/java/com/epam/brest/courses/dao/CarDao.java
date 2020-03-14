package com.epam.brest.courses.dao;

import com.epam.brest.courses.model.Car;

import java.util.List;
import java.util.Optional;

/**
 * Car DAO Interface.
 */
public interface CarDao {

    /**
     * Get all cars.
     *
     * @return list of all cars
     */
    List<Car> findAll();

    /**
     * Get car with specified id.
     *
     * @param carId car id
     * @return car by id
     */
    Optional<Car> findById(Integer carId);

    /**
     * Persist new car.
     *
     * @param Car car
     * @return persisted car id.
     */
    Integer create(Car car);

    /**
     * Update car.
     *
     * @param car car
     * @return number of updated records in the database.
     */
    int update(Car car);

    /**
     * Delete car with specified id.
     *
     * @param carId
     * @return number of updated records in the database.
     */
    int delete(Integer carId);

}
