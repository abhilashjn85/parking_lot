package com.gojek.parking;

import com.gojek.parking.constant.Constant;
import com.gojek.parking.exception.ParkingLotException;
import com.gojek.parking.printer.ParkingPrinter;
import com.gojek.parking.util.ErrorCodes;

import java.io.*;

public class Main {

    public static void main(String[] args) {

        System.out.println(Constant.PARKING_LOT_MESSAGE);
        System.out.println(ParkingPrinter.stringifyParkingInstruction());

        BufferedReader reader = null;
        String input;
        switch (args.length) {
            case 0:
                System.out.println(Constant.EXIT_MESSAGE);
                while (Boolean.TRUE) {
                    reader = new BufferedReader(new InputStreamReader(System.in));
                    try {
                        input = reader.readLine().trim();
                        if (Constant.EXIT.equalsIgnoreCase(input)) {
                            break;
                        }
                        /**
                         * //TODO: code logic
                         */
                    } catch (IOException e) {
                        throw new ParkingLotException(ErrorCodes.INVALID_INPUT, "Invalid Input data, Read Input Instruction Again.");
                    }
                }

            case 1:
                File inputFile = new File(args[0]);
                try {
                    reader = new BufferedReader(new FileReader(inputFile));
                    while ((input = reader.readLine()) != null) {
                        input = input.trim();
                        /**
                         * //TODO: code logic
                         */
                    }
                } catch (IOException e) {
                    throw new ParkingLotException(ErrorCodes.INVALID_FILE, "Invalid File.");

                } finally {
                    try {
                        if (reader != null)
                            reader.close();
                    } catch (IOException e) {
                    }
                }
            default:
                System.out.println("Invalid input. Please read instruction. ErrorCode: ");
        }
    }
}
