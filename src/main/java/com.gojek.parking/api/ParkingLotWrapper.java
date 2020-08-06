package com.gojek.parking.api;

import com.gojek.parking.enums.ParkingLotIntent;

public class ParkingLotWrapper {

    private ParkingLot parkingLot;
    private ParkingLotIntent parkingLotIntent;

    public ParkingLotWrapper() {

    }

    public ParkingLotWrapper(ParkingLot parkingLot, ParkingLotIntent parkingLotIntent) {
        this.parkingLot = parkingLot;
        this.parkingLotIntent = parkingLotIntent;
    }


    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingLotIntent getParkingLotIntent() {
        return parkingLotIntent;
    }

    public void setParkingLotIntent(ParkingLotIntent parkingLotIntent) {
        this.parkingLotIntent = parkingLotIntent;
    }
}
