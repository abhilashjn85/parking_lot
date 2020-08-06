package com.gojek.parking;

import com.gojek.parking.api.ParkingLotWrapper;
import com.gojek.parking.constant.Constant;
import com.gojek.parking.exception.NoParkingLotException;
import com.gojek.parking.exception.ParkingLotException;
import com.gojek.parking.printer.ParkingPrinter;
import com.gojek.parking.processor.BaseProcessor;
import com.gojek.parking.processor.ParkingLotProcessor;
import com.gojek.parking.resolver.InputResolver;
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

                        try {
                            ParkingLotWrapper wrapper = InputResolver.resolveAndValidate(input);
                            processor.process(wrapper.getParkingLot(), wrapper.getParkingLotIntent());
                        } catch(ParkingLotException e) {
                            System.out.println(e.getMessage() + ", ErrorCode: " +e.getErrorCode());
                        } catch (NoParkingLotException e) {
                            System.out.println(e.getMessage() + ", ErrorCode: " +e.getErrorCode());
                        }

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

                        try {
                            ParkingLotWrapper wrapper = InputResolver.resolveAndValidate(input);
                            processor.process(wrapper.getParkingLot(), wrapper.getParkingLotIntent());
                        } catch(ParkingLotException e) {
                            System.out.println(e.getMessage() + ", ErrorCode: " +e.getErrorCode());
                        } catch (NoParkingLotException e) {
                            System.out.println(e.getMessage() + ", ErrorCode: " +e.getErrorCode());
                        }
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
                System.out.println("Invalid Input. Please read the instruction carefully, ErrorCode: " + ErrorCodes.INVALID_INPUT);
        }
    }
}
