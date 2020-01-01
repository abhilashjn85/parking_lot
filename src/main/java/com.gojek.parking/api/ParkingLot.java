package com.gojek.parking.api;

import com.gojek.parking.exception.ParkingLotException;
import com.gojek.parking.util.ErrorCodes;

public class ParkingLot extends Base {

    private Integer capacity = Integer.MAX_VALUE;
    private Vehicle vehicle;

    ParkingLot(ParkingLot.Builder builder) {
        this.capacity = builder.capacity;
        this.vehicle = builder.vehicle;
    }

    public static class Builder {
        private Integer capacity = Integer.MAX_VALUE;
        private Vehicle vehicle;

        public static ParkingLot.Builder newInstance() {
            return new ParkingLot.Builder();
        }

        Builder() {
        }

        public ParkingLot.Builder setCapacity(int capacity) {
            if (capacity < 0) {
                throw new ParkingLotException(ErrorCodes.CONSTRAINTS_VIOLATION, "Capacity can not less than Zero.");
            } else {
                this.capacity = capacity;
            }

            return this;
        }

        public ParkingLot.Builder setVehicle(Vehicle vehicle) {
            this.vehicle = vehicle;
            return this;
        }

        public ParkingLot build() {
            return new ParkingLot(this);
        }
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
