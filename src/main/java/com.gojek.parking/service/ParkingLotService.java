package com.gojek.parking.service;

import com.gojek.parking.api.ParkingLot;
import com.gojek.parking.api.Vehicle;
import com.gojek.parking.exception.ParkingLotException;

import java.util.Optional;

public interface ParkingLotService {

    void createParkingLot(ParkingLot parkingLot) throws ParkingLotException;
    Optional<Integer> park(IssueSlotService slotService, Vehicle vehicle) throws ParkingLotException;
    void unPark(int level, int slotNumber) throws ParkingLotException;
    void fetchStatus() throws ParkingLotException;
    void fetchRegistrationNumberForColor(String color) throws ParkingLotException;
    void fetchSlotNumbersForColor(String colour) throws ParkingLotException;
    int fetchSlotNumberFromRegistrationNumber( String registrationNo) throws ParkingLotException;
}
