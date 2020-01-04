package com.gojek.parking.service;

import com.gojek.parking.api.Vehicle;

import java.util.List;

public interface IssueSlotService {

    int parkCar(Vehicle vehicle);

    Boolean leaveCar(int slotNumber);

    List<String> fetchStatus();

    List<String> fetchRegistrationNumberByColor(String color);

    List<Integer> fetchSlotNumbersByColor(String colour);

    int fetchSlotNumberByRegistrationNumber(String registrationNo);

    int fetchAvailableSlot();

    void refresh();
}
