package com.epam.brest.courses.dao;

import com.epam.brest.courses.model.Rent;

import java.util.List;
import java.util.Optional;

/**
 * Rent DAO Interface.
 */
public interface RentDao {

    /**
     * Get all rents.
     *
     * @return list of all rents
     */
    List<Rent> findAll();

    /**
     * Get all rents with specified car id.
     *
     * @param carId car id
     * @return list of rents by car id
     */
    List<Rent> findByCarId(Integer carId);

    /**
     * Get rent with specified id.
     *
     * @param rentId rent id
     * @return rent by id
     */
    Optional<Rent> findById(Integer rentId);

    /**
     * Persist new rent.
     *
     * @param rent rent
     * @return persisted rent id.
     */
    Integer create(Rent rent);

    /**
     * Update rent.
     *
     * @param rent rent
     * @return number of updated records in the database.
     */
    int update(Rent rent);

    /**
     * Delete rent with specified id.
     *
     * @param rentId car id
     * @return number of updated records in the database.
     */
    int delete(Integer rentId);

}
