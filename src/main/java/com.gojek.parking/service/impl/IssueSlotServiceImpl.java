package com.gojek.parking.service.impl;

import com.gojek.parking.api.Vehicle;
import com.gojek.parking.service.IssueSlotService;
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
            slotVehicleRegistrationMap.put(i, null);
            availableSlotsMap.put(i, Boolean.TRUE);
        }
    }

    @Override
    public int parkCar(Vehicle vehicle)
    {
        if (availableSlotsMap.size() == 0) {
            return -1;
        } else if (slotVehicleRegistrationMap.containsValue(vehicle.getRegistrationNumber())) {
            return -1;
        }

        int availableSlotFirstKey = availableSlotsMap.firstKey().intValue();
        availableSlotsMap.remove(availableSlotFirstKey);
        slotVehicleRegistrationMap.put(availableSlotFirstKey, vehicle.getRegistrationNumber());
        vehicleRegistrationMap.put(vehicle.getRegistrationNumber(), vehicle);

        return availableSlotFirstKey;
    }

    @Override
    public Boolean leaveCar(int slotNumber)
    {
        if (null == slotVehicleRegistrationMap.get(slotNumber)) {
            return false;
        }

        availableSlotsMap.remove(slotNumber);
        slotVehicleRegistrationMap.put(slotNumber, null);
        return true;
    }

    @Override
    public List<String> fetchStatus() {
        List<String> results = new ArrayList<>();
        for (int i = 1; i <= capacity.get(); i++) {
            String registrationKey = slotVehicleRegistrationMap.get(i);
            Vehicle vehicle = vehicleRegistrationMap.get(registrationKey);
            if (null != vehicle) {
                results.add(i + "\t\t" + registrationKey + "\t\t" + vehicle.getColor());
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
        return result;
    }

    @Override
    public int fetchAvailableSlot() {
        return availableSlotsMap.firstKey();
    }

    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    @Override
    public void refresh() {
        this.capacity = new AtomicInteger();
        slotVehicleRegistrationMap = new HashMap<>();
        vehicleRegistrationMap = new HashMap<>();
        instance = null;
    }
}
