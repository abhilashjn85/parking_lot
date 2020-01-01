package com.gojek.parking.api;

import com.gojek.parking.enums.VehicleSize;
import com.gojek.parking.enums.VehicleType;

public class Vehicle extends Base {

    private String registrationNumber;
    private VehicleType vehicleType = VehicleType.FOUR_WHEELER;
    private VehicleSize vehicleSize = VehicleSize.MEIDUM;
    private String color;

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public VehicleSize getVehicleSize() {
        return vehicleSize;
    }

    public void setVehicleSize(VehicleSize vehicleSize) {
        this.vehicleSize = vehicleSize;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
