package com.gojek.parking;

import com.gojek.parking.constant.Constant;
import com.gojek.parking.exception.ParkingLotException;
import com.gojek.parking.printer.ParkingPrinter;
import com.gojek.parking.processor.BaseProcessor;
import com.gojek.parking.processor.ParkingLotProcessor;
import com.gojek.parking.service.impl.IssueSlotServiceImpl;
import com.gojek.parking.service.impl.ParkingLotServiceImpl;
import com.gojek.parking.util.ErrorCodes;

import java.io.*;

public class Main {

    private static BaseProcessor init() {
        BaseProcessor processor = new ParkingLotProcessor();
        processor.setParkingLotService(new ParkingLotServiceImpl());
        processor.setIssueSlotService(new IssueSlotServiceImpl());

        return processor;
    }

    public static void main(String[] args) {

        BaseProcessor processor = init();
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

                        formParkingLot(input);
                        formParkingIntent(input);

                        processor.process(null, null);

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

    private static void formParkingIntent(String input) {
    }

    private static void formParkingLot(String input) {
    }
}
