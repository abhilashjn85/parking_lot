package com.gojek.parking.exception;

public class NoParkingLotException extends BaseException {

    public NoParkingLotException(String code, String message) {
        super(code, message);
    }
}
