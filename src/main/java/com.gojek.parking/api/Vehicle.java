package com.gojek.parking.api;

import com.gojek.parking.enums.VehicleSize;
import com.gojek.parking.enums.VehicleType;
import com.gojek.parking.exception.ParkingLotException;
import com.gojek.parking.util.ErrorCodes;

public class Vehicle extends Base {

    private String registrationNumber;
    private VehicleType vehicleType = VehicleType.FOUR_WHEELER;
    private VehicleSize vehicleSize = VehicleSize.MEIDUM;
    private String color;

    Vehicle(Vehicle.Builder builder) {
        this.registrationNumber = builder.registrationNumber;
        this.vehicleType = builder.vehicleType;
        this.vehicleSize = builder.vehicleSize;
        this.color = builder.color;
    }

    public static class Builder {

        private String registrationNumber = null;
        private VehicleType vehicleType = VehicleType.FOUR_WHEELER;
        private VehicleSize vehicleSize = VehicleSize.MEIDUM;
        private String color;

        public static Vehicle.Builder newInstance() {
            return new Vehicle.Builder();
        }

        Builder() {
        }

        public Vehicle.Builder setRegistrationNumber(String registrationNumber) {
            if (null == registrationNumber || "".equals(registrationNumber)) {
                throw new ParkingLotException(ErrorCodes.INVALID_DATA, "Registration number is invalid.");
            } else {
                this.registrationNumber = registrationNumber;
            }

            return this;
        }

        public  Vehicle.Builder setColor(String color) {
            if (null == color || "".equals(color)) {
                throw new ParkingLotException(ErrorCodes.INVALID_DATA, "Color is invalid.");
            } else {
                this.color = color;
            }
            return this;
        }

        public Vehicle.Builder setVehicleType(VehicleType vehicleType) {
            this.vehicleType = vehicleType;
            return this;
        }

        public Vehicle.Builder setVehicleSize(VehicleSize vehicleSize) {
            this.vehicleSize = vehicleSize;
            return this;
        }

        public Vehicle build() {
            return new Vehicle(this);
        }
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public VehicleSize getVehicleSize() {
        return vehicleSize;
    }

    public String getColor() {
        return color;
    }
}
