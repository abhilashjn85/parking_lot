package com.gojek.parking.service.impl;

import com.gojek.parking.api.ParkingLot;
import com.gojek.parking.api.Vehicle;
import com.gojek.parking.constant.Constant;
import com.gojek.parking.exception.ParkingLotException;
import com.gojek.parking.service.ParkingLotService;
import com.gojek.parking.util.Validator;

import java.util.List;
import java.util.stream.Collectors;

public class ParkingLotServiceImpl implements ParkingLotService {

    private IssueSlotServiceImpl issueSlotServiceImpl;

    @Override
    public void createParkingLot(ParkingLot parkingLot) throws ParkingLotException {
        issueSlotServiceImpl = IssueSlotServiceImpl.getInstance(parkingLot.getCapacity());
        System.out.println("Created a parking lot with "+parkingLot.getCapacity()+ " slots");
    }

    @Override
    public Integer park(Vehicle vehicle) throws ParkingLotException {

        Validator.validateParking(issueSlotServiceImpl);
        int parkCode = issueSlotServiceImpl.parkCar(vehicle);
        if(Constant.ALREADY_PARKED_CODE.intValue() == parkCode) {
            System.out.println("Vehicle is already parked");
            return parkCode;

        } else if (Constant.NOT_ENOUGH_SPACE_CODE.intValue() == parkCode) {
            System.out.println("Sorry, parking lot is full");
            return parkCode;

        } else {
            System.out.println("Allocated slot number: " + parkCode);
        }
        return parkCode;
    }

    @Override
    public void unPark(ParkingLot parkingLot) throws ParkingLotException {

        Validator.validateParking(issueSlotServiceImpl);
        if(issueSlotServiceImpl.leaveCar(parkingLot.getSlotNumber())) {
            System.out.println("Slot number " + parkingLot.getSlotNumber() + " is free");
        }
    }

    @Override
    public void fetchStatus() throws ParkingLotException {

        Validator.validateParking(issueSlotServiceImpl);
        List<String> statuses = issueSlotServiceImpl.fetchStatus();
        System.out.println(String.join("\n", statuses));
    }

    @Override
    public void fetchRegistrationNumbersByColor(String color) throws ParkingLotException {

        Validator.validateParking(issueSlotServiceImpl);
        List<String> registrationkeys = issueSlotServiceImpl.fetchRegistrationNumberByColor(color);
        System.out.println(String.join(", ", registrationkeys));
    }

    @Override
    public void fetchSlotNumbersByColor(String colour) throws ParkingLotException {

        Validator.validateParking(issueSlotServiceImpl);
        List<Integer> slots = issueSlotServiceImpl.fetchSlotNumbersByColor(colour);

        List<String> soltsString = slots.stream()
                .map(s -> String.valueOf(s))
                .collect(Collectors.toList());
        System.out.println(String.join(", ", soltsString));
    }

    @Override
    public Integer fetchSlotNumberByRegistrationNumber(String registrationNo) throws ParkingLotException {

        Validator.validateParking(issueSlotServiceImpl);
        int slot = issueSlotServiceImpl.fetchSlotNumberByRegistrationNumber(registrationNo);
        System.out.println(slot);
        return slot;
    }
}
