package com.gojek.parking.service.impl;

import com.gojek.parking.api.Vehicle;
import com.gojek.parking.exception.ParkingLotException;
import com.gojek.parking.service.IssueSlotService;
import com.gojek.parking.util.ErrorCodes;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class IssueSlotServiceImpl implements IssueSlotService {

    public IssueSlotServiceImpl() {

    }

    private AtomicInteger capacity = new AtomicInteger();
    private Map<Integer, String> slotVehicleRegistrationMap;
    private TreeMap<Integer, Boolean> availableSlotsMap = new TreeMap<>();
    private Map<String, Vehicle> vehicleRegistrationMap = new HashMap<>();

    private static IssueSlotServiceImpl instance = null;

    public static IssueSlotServiceImpl getInstance(int capacity) {
        if (instance == null) {
            synchronized (IssueSlotServiceImpl.class) {
                if (null == instance) {
                    instance = new IssueSlotServiceImpl(capacity);
                }
            }
        }
        return instance;
    }

    private IssueSlotServiceImpl(int capacity) {
        this.capacity.set(capacity);
        slotVehicleRegistrationMap = new ConcurrentHashMap<>();
        for (int i = 1; i <= capacity; i++) {
            availableSlotsMap.put(i, Boolean.TRUE);
        }
    }

    @Override
    public int parkCar(Vehicle vehicle) {

        if (availableSlotsMap.size() == 0) {
            return -1;
        } else if(null != vehicleRegistrationMap.get(vehicle.getRegistrationNumber())) {
            return -2;
        }

        int availableSlotFirstKey = availableSlotsMap.firstKey().intValue();
        availableSlotsMap.remove(availableSlotFirstKey);
        slotVehicleRegistrationMap.put(availableSlotFirstKey, vehicle.getRegistrationNumber());
        vehicleRegistrationMap.put(vehicle.getRegistrationNumber(), vehicle);

        return availableSlotFirstKey;
    }

    @Override
    public Boolean leaveCar(int slotNumber) {

        if (null == slotVehicleRegistrationMap.get(slotNumber)) {
            System.out.println("No vehicle is parked with this slot.");
            return false;
        }

        availableSlotsMap.put(slotNumber, Boolean.TRUE);
        vehicleRegistrationMap.remove(slotVehicleRegistrationMap.get(slotNumber));
        slotVehicleRegistrationMap.remove(slotNumber);
        return true;
    }

    @Override
    public List<String> fetchStatus() {
        List<String> results = new ArrayList<>();
        for (int i = 1; i <= capacity.get(); i++) {
            String registrationKey = slotVehicleRegistrationMap.get(i);
            Vehicle vehicle = vehicleRegistrationMap.get(registrationKey);
            if (null != vehicle) {
                results.add(i + " " + registrationKey + " " + vehicle.getColor());
            }
        }
        return results;
    }

    @Override
    public List<String> fetchRegistrationNumberByColor(String color) {
        List<String> results = new ArrayList<>();
        for (int i = 1; i <= capacity.get(); i++) {
            String registrationKey = slotVehicleRegistrationMap.get(i);
            Vehicle vehicle = vehicleRegistrationMap.get(registrationKey);
            if (null != vehicle && color.equalsIgnoreCase(vehicle.getColor())) {
                results.add(registrationKey);
            }
        }
        return results;
    }

    @Override
    public List<Integer> fetchSlotNumbersByColor(String colour) {
        List<Integer> results = new ArrayList<>();
        for (int i = 1; i <= capacity.get(); i++) {
            String registrationKey = slotVehicleRegistrationMap.get(i);
            Vehicle vehicle = vehicleRegistrationMap.get(registrationKey);

            if (null != vehicle && colour.equalsIgnoreCase(vehicle.getColor())) {
                results.add(i);
            }
        }
        return results;
    }

    @Override
    public int fetchSlotNumberByRegistrationNumber(String registrationNumber) {

        int result = -1;
        for (int i = 1; i <= capacity.get(); i++) {
            String registrationKey = slotVehicleRegistrationMap.get(i);
            Vehicle vehicle = vehicleRegistrationMap.get(registrationKey);
            if (null != vehicle && registrationNumber.equalsIgnoreCase(registrationKey)) {
                result = i;
            }
        }

        if(result == -1) {
            throw new ParkingLotException(ErrorCodes.OBJ_NOT_FOUND, "Not found");
        }
        return result;
    }

    @Override
    public int fetchAvailableSlot() {
        return availableSlotsMap.firstKey();
    }

    @Override
    public void refresh() {
        this.capacity = new AtomicInteger();
        slotVehicleRegistrationMap = new HashMap<>();
        vehicleRegistrationMap = new HashMap<>();
        instance = null;
    }
}
