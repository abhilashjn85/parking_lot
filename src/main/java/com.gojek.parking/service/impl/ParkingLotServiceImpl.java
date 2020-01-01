package com.gojek.parking.service.impl;

import com.gojek.parking.api.ParkingLot;
import com.gojek.parking.api.Vehicle;
import com.gojek.parking.exception.ParkingLotException;
import com.gojek.parking.service.IssueSlotService;
import com.gojek.parking.service.ParkingLotService;

import java.util.Optional;

public class ParkingLotServiceImpl implements ParkingLotService {

    @Override
    public void createParkingLot(ParkingLot parkingLot) throws ParkingLotException {

    }

    @Override
    public Optional<Integer> park(IssueSlotService slotService, Vehicle vehicle) throws ParkingLotException {
        return Optional.empty();
    }

    @Override
    public void unPark(ParkingLot parkingLot) throws ParkingLotException {

    }

    @Override
    public void fetchStatus() throws ParkingLotException {

    }

    @Override
    public void fetchRegistrationNumbersByColor(String color) throws ParkingLotException {

    }

    @Override
    public void fetchSlotNumbersByColor(String colour) throws ParkingLotException {

    }

    @Override
    public Optional<Integer> fetchSlotNumberByRegistrationNumber(String registrationNo) throws ParkingLotException {
        return Optional.empty();
    }
}
