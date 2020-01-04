package com.gojek.parking.service;

import com.gojek.parking.api.ParkingLot;
import com.gojek.parking.api.Vehicle;
import com.gojek.parking.exception.ParkingLotException;

public interface ParkingLotService {

    void createParkingLot(ParkingLot parkingLot) throws ParkingLotException;
    Integer park(Vehicle vehicle) throws ParkingLotException;
    void unPark(ParkingLot parkingLot) throws ParkingLotException;
    void fetchStatus() throws ParkingLotException;
    void fetchRegistrationNumbersByColor(String color) throws ParkingLotException;
    void fetchSlotNumbersByColor(String colour) throws ParkingLotException;
    Integer fetchSlotNumberByRegistrationNumber(String registrationNo) throws ParkingLotException;
}
