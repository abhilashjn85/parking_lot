package com.gojek.parking.exception;

public class ParkingLotException extends BaseException {

    public ParkingLotException(String code, String message) {
        super(code, message);
    }

    public ParkingLotException(String code, String message, String userMessage) {
        super(code, message, userMessage);
    }

    public ParkingLotException(String message) {
        super(message);
    }

    public ParkingLotException(String code, Throwable throwable) {
        super(code, throwable);
    }
}
