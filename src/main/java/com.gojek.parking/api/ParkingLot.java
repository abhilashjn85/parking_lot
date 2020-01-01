package com.gojek.parking.api;

public class ParkingLot extends Base {

    private Integer capacity = Integer.MAX_VALUE;
    private Vehicle vehicle;

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
