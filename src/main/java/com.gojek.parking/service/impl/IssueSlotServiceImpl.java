package com.gojek.parking.service.impl;

import com.gojek.parking.exception.ParkingLotException;
import com.gojek.parking.service.IssueSlotService;
import com.gojek.parking.util.ErrorCodes;

import java.util.HashMap;
import java.util.Map;

public class IssueSlotServiceImpl implements IssueSlotService {

    //TODO: issue with multithreading
    private static Map<Integer, Boolean> slotCountStatusMap = new HashMap<>();

    public IssueSlotServiceImpl() {

        //TODO: think of available slots and put in separate set.
    }

    @Override
    public int fetchAvailableSlotNumber() {
        return 0;
    }

    public void commitSlot(Integer slotNumber) {

        if(Boolean.FALSE == slotCountStatusMap.get(slotNumber)) {
            slotCountStatusMap.put(slotNumber, Boolean.TRUE);
        }
        throw new ParkingLotException(ErrorCodes.DUPLICATE_SLOT_FOUND, "Slot already allocated, Please try again.");
    }
}
