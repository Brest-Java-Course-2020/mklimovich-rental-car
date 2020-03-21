package com.epam.brest.courses.model.dto;

import java.math.BigDecimal;
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
     * Brand Car.
     */
    private String brandCar;

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
     * @param brandCar brand car
     */
    public CarDto(String brandCar) {
        this.brandCar = brandCar;
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
     * Returns <code>String</code> representation of this brandCar.
     *
     * @return brandCar Brand Car.
     */
    public String getBrandCar() {return brandCar;}

    /**
     * Sets the Brand Car.
     *
     * @param brandCar Brand Car.
     */
    public void setBrandCar(String brandCar) {this.brandCar = brandCar;}

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
                ", brandCar='" + brandCar + '\'' +
                ", countRent=" + countRent +
                '}';
    }
}

