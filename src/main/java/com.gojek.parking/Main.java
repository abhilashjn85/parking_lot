package com.gojek.parking;

import com.gojek.parking.constant.Constant;
import com.gojek.parking.printer.ParkingPrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {

        System.out.println(Constant.PARKING_LOT_MESSAGE);
        System.out.println(ParkingPrinter.stringifyParkingInstruction());

        BufferedReader reader;
        String input;
        switch(args.length) {
            case 0:
                System.out.println(Constant.EXIT_MESSAGE);
                while(Boolean.TRUE) {
                    reader = new BufferedReader(new InputStreamReader(System.in));
                    try {
                        input = reader.readLine().trim();
                        if (Constant.EXIT.equalsIgnoreCase(input)) {
                            break;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        }
    }
}
