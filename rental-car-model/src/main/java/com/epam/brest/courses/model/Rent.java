package com.epam.brest.courses.model;

public class Rent {

    private Integer rentId;

    private String dateRent;

    private Integer carId;

    public Integer getRentId() {
        return rentId;
    }

    public Rent setRentId(Integer rentId) {
        this.rentId = rentId;
        return this;
    }

    public String getDateRent() {
        return dateRent;
    }

    public Rent setDateRent(String dateRent) {
        this.dateRent = dateRent;
        return this;
    }

    public Integer getCarId() {
        return carId;
    }

    public Rent setCarId(Integer carId) {
        this.carId = carId;
        return this;
    }

    @Override
    public String toString() {
        return "Rent{" +
                "rentId=" + rentId +
                ", dateRent='" + dateRent + '\'' +
                ", carId=" + carId +
                '}';
    }
}
