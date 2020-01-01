package com.gojek.parking.processor;

import com.gojek.parking.api.ParkingLot;
import com.gojek.parking.enums.ParkingLotIntent;
import com.gojek.parking.service.IssueSlotService;
import com.gojek.parking.service.ParkingLotService;

/**
 * Base Processor that processes a Request (parkingLot and issueSlot).
 */
public interface BaseProcessor {

    void setParkingLotService(ParkingLotService parkingLotService);
    void setIssueSlotService(IssueSlotService issueSlotService);
    void process(ParkingLot parkingLot, ParkingLotIntent intent);
}
