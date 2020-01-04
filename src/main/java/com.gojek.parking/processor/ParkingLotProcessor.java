package com.gojek.parking.processor;

import com.gojek.parking.api.ParkingLot;
import com.gojek.parking.enums.ParkingLotIntent;
import com.gojek.parking.exception.ParkingLotException;
import com.gojek.parking.service.IssueSlotService;
import com.gojek.parking.service.ParkingLotService;
import com.gojek.parking.service.impl.IssueSlotServiceImpl;
import com.gojek.parking.util.ErrorCodes;

public class ParkingLotProcessor implements BaseProcessor {

    private ParkingLotService parkingLotService;

    private IssueSlotService issueSlotService;

    @Override
    public void setParkingLotService(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @Override
    public void setIssueSlotService(IssueSlotService issueSlotService) {
        this.issueSlotService = issueSlotService;
    }

    @Override
    public void process(ParkingLot parkingLot, ParkingLotIntent intent) {

        switch (intent) {

            case CREATE :
                IssueSlotServiceImpl.getInstance(parkingLot.getCapacity());
                parkingLotService.createParkingLot(parkingLot);
                break;

            case PARK:
                parkingLotService.park(parkingLot.getVehicle());
                break;

            case STATUS:
                parkingLotService.fetchStatus();
                break;

            case REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR:
                parkingLotService.fetchRegistrationNumbersByColor(parkingLot.getVehicle().getColor());
                break;

            case SLOT_NUMBERS_FOR_CARS_WITH_COLOUR:
                parkingLotService.fetchSlotNumbersByColor(parkingLot.getVehicle().getColor());
                break;

            case UNPARK:
                parkingLotService.unPark(parkingLot);
                break;

            case SLOT_NUMBER_FOR_REGISTRATION_NUMBER:
                parkingLotService.fetchSlotNumberByRegistrationNumber(parkingLot.getVehicle().getRegistrationNumber());
                break;

            default:
                throw new ParkingLotException(ErrorCodes.INVALID_INPUT, "Undefined input given to intent.");

        }
    }
}
