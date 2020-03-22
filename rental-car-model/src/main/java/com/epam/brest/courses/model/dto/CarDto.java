package com.epam.brest.courses.model.dto;

import java.math.BigInteger;

/**
 * POJO Car for model.
 */
public class CarDto {

    /**
     * Car Id.
     */
    private Integer carId;

    /**
     * Model
     */
    private String model;

    /**
     * Count Rent of the Car.
     */
    private BigInteger countRent;

    /**
     * Constructor without arguments.
     */
    public CarDto() {
    }

    /**
     * Constructor with brand car.
     *
     * @param model brand car
     */
    public CarDto(String model) {
        this.model = model;
    }

    /**
     * Returns <code>Integer</code> representation of this carId.
     *
     * @return carId Car Id.
     */
    public Integer getCarId() {return carId;}

    /**
     * Sets the car's identifier.
     *
     * @param carId Car Id.
     */
    public void setCarId(Integer carId) {this.carId = carId;}

    /**
     * Returns <code>String</code> representation of this model.
     *
     * @return model Model Car.
     */
    public String getModel() {return model;}

    /**
     * Sets the Model.
     *
     * @param model Model Car.
     */
    public void setModel(String model) {this.model = model;}

    /**
     * Returns <code>BigInteger</code> representation of count rent
     * for the Car.
     *
     * @return carId.
     */
    public BigInteger getCountRent() {return countRent;}

    /**
     * Sets the car's count rent.
     *
     * @param countRent Count rent.
     */
    public void setCountRent(BigInteger countRent) {this.countRent = countRent;}

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "CarDto{" +
                "carId=" + carId +
                ", model='" + model + '\'' +
                ", countRent=" + countRent +
                '}';
    }
}

