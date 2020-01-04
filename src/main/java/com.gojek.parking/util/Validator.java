package com.gojek.parking.util;

import com.gojek.parking.exception.ParkingLotException;
import com.gojek.parking.service.impl.IssueSlotServiceImpl;

public class Validator {

    public static Boolean validateParking(IssueSlotServiceImpl issueSlotService) {

        if(null == issueSlotService) {
            throw new ParkingLotException(ErrorCodes.CONSTRAINTS_VIOLATION, "Please create parking slot first.");
        }
        return Boolean.TRUE;
    }
}
