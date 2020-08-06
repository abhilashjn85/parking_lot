package com.gojek.parking.data;

import com.gojek.parking.api.ParkingLot;
import com.gojek.parking.api.ParkingLotWrapper;
import com.gojek.parking.api.Vehicle;
import com.gojek.parking.enums.ParkingLotIntent;

public class ParkingTestData {

    public static ParkingLotWrapper dataParkingLotWithCapacity(int capacity) {
        return new ParkingLotWrapper(ParkingLot.Builder
                .newInstance()
                .setCapacity(capacity)
                .build(),
                ParkingLotIntent.CREATE);
    }

    public static ParkingLotWrapper dataParkingLotForPark(String registrationNumber, String color) {
        return new ParkingLotWrapper(ParkingLot.Builder
                .newInstance()
                .setVehicle(Vehicle.Builder
                        .newInstance()
                        .setRegistrationNumber(registrationNumber)
                        .setColor(color)
                        .build())
                .build(),
                ParkingLotIntent.PARK);
    }

    public static ParkingLotWrapper dataParkingLotForUnPark(Integer slotNumber) {
        return new ParkingLotWrapper(ParkingLot.Builder
                .newInstance()
                .setSlotNumber(slotNumber)
                .build(),
                ParkingLotIntent.UNPARK);
    }
}
