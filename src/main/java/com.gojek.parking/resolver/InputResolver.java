package com.gojek.parking.resolver;

import com.gojek.parking.api.ParkingLot;
import com.gojek.parking.api.ParkingLotWrapper;
import com.gojek.parking.api.Vehicle;
import com.gojek.parking.constant.Constant;
import com.gojek.parking.enums.ParkingLotIntent;
import com.gojek.parking.exception.ParkingLotException;
import com.gojek.parking.util.ErrorCodes;
import com.gojek.parking.util.Utils;


public class InputResolver {

    public static ParkingLotWrapper resolveAndValidate(String input) {

        ParkingLotWrapper parkingLotWrapper;

        String text[] = input.split(" ");
        if(text.length == 2 && Constant.CREATE_PARKING_LOT.equalsIgnoreCase(text[0])) {
            int capacity;
            try {
                capacity = Integer.parseInt(text[1]);
            } catch (Exception e) {
                throw new ParkingLotException(ErrorCodes.INVALID_INPUT, "Parking lot size should be number.");
            }
            return new ParkingLotWrapper(ParkingLot.Builder
                    .newInstance()
                    .setCapacity(capacity)
                    .build(),
                    ParkingLotIntent.CREATE);

        } else if (text.length == 3 && ParkingLotIntent.PARK.toString().equalsIgnoreCase(text[0])) {

            if(Utils.checkIfStringContainsCharacters(text[2])) {
                return new ParkingLotWrapper(ParkingLot.Builder
                        .newInstance()
                        .setVehicle(Vehicle.Builder
                                .newInstance()
                                .setRegistrationNumber(text[1])
                                .setColor(text[2])
                                .build())
                        .build(),
                        ParkingLotIntent.PARK);
            }
            throw new ParkingLotException(ErrorCodes.INVALID_INPUT, "Parking lot color should be contain all string.");

        } else if (text.length == 2 && ParkingLotIntent.LEAVE.toString().equalsIgnoreCase(text[0])) {

            int slotNumber;
            try {
                slotNumber = Integer.parseInt(text[1]);
            } catch (Exception e) {
                throw new ParkingLotException(ErrorCodes.INVALID_INPUT, "Slot number should be number.");
            }
            return new ParkingLotWrapper(ParkingLot.Builder
                    .newInstance()
                    .setSlotNumber(slotNumber)
                    .build(),
                    ParkingLotIntent.UNPARK);

        } else if(text.length == 1 && ParkingLotIntent.STATUS.toString().equalsIgnoreCase(text[0])) {

            return new ParkingLotWrapper(null,
                    ParkingLotIntent.STATUS);

        } else if (text.length == 2 && ParkingLotIntent.REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR.toString().equalsIgnoreCase(text[0])) {


            if(Utils.checkIfStringContainsCharacters(text[1])) {

                return new ParkingLotWrapper(ParkingLot.Builder
                        .newInstance()
                        .setVehicle(Vehicle.Builder
                                .newInstance()
                                .setColor(text[1])
                                .build())
                        .build(),
                        ParkingLotIntent.REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR);
            }
            throw new ParkingLotException(ErrorCodes.INVALID_INPUT, "Parking lot color should be contain all string.");

        } else if(text.length == 2 && ParkingLotIntent.SLOT_NUMBERS_FOR_CARS_WITH_COLOUR.toString().equalsIgnoreCase(text[0])) {

            if(Utils.checkIfStringContainsCharacters(text[1])) {

                return new ParkingLotWrapper(ParkingLot.Builder
                        .newInstance()
                        .setVehicle(Vehicle.Builder
                                .newInstance()
                                .setColor(text[1])
                                .build())
                        .build(),
                        ParkingLotIntent.SLOT_NUMBERS_FOR_CARS_WITH_COLOUR);
            }
            throw new ParkingLotException(ErrorCodes.INVALID_INPUT, "Parking lot color should be contain all string.");

        } else if (text.length == 2 && ParkingLotIntent.SLOT_NUMBER_FOR_REGISTRATION_NUMBER.toString().equalsIgnoreCase(text[0])) {

            return new ParkingLotWrapper(ParkingLot.Builder
                    .newInstance()
                    .setVehicle(Vehicle.Builder
                            .newInstance()
                            .setRegistrationNumber(text[1])
                            .build())
                    .build(),
                    ParkingLotIntent.SLOT_NUMBER_FOR_REGISTRATION_NUMBER);
        }

        throw new ParkingLotException(ErrorCodes.INVALID_INPUT, "Input is invalid. Please read instruction.");
    }
}
