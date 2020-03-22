package com.epam.brest.courses.model;

public class Car {

    private Integer carId;

    private String model;

    private String color;

    public Integer getCarId() {
        return carId;
    }

    public Car setCarId(Integer carId) {

        this.carId = carId;
        return  this;
    }

    public String getModel() {
        return model;
    }

    public Car setModel(String model) {

        this.model = model;
        return this;

    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
