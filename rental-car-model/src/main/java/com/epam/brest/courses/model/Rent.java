package com.epam.brest.courses.model;

public class Rent {

    private Integer rentId;

    private String dateRent;

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

    @Override
    public String toString() {
        return "Rent{" +
                "rentId=" + rentId +
                ", dateRent='" + dateRent + '\'' +
                '}';
    }
}
