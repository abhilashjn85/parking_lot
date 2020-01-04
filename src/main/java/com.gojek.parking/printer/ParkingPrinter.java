package com.gojek.parking.printer;

import com.gojek.parking.constant.Constant;

public class ParkingPrinter {

    public static String stringifyParkingInstruction()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(Constant.PARKING_LOT_MESSAGE).append("\n\n")
                .append("Please follow below instructions to operate our system, Small Instruction: Anything start with '#' means it needs input from console: suffix with '_num' means number, '_string' means alphanumeric or word.").append("\n")
                .append("1. Creation of parking lot system").append(Constant.DASH_SEPARATOR).append(Constant.ENTER).append("create_parking_lot #capacity_num)").append("\n")
                .append("2. For parking a car").append(Constant.DASH_SEPARATOR).append(Constant.ENTER).append("park #registration_num #color_string").append("\n")
                .append("3. For un-parking car").append(Constant.DASH_SEPARATOR).append(Constant.ENTER).append("leave #slot_numer_num").append("\n")
                .append("4. For printing current status of parking").append(Constant.DASH_SEPARATOR).append(Constant.ENTER).append("status").append("\n")
                .append("5. For printing cars registration number for a given color").append(Constant.DASH_SEPARATOR).append(Constant.ENTER).append("registration_numbers_for_cars_with_color #car_color_string").append("\n")
                .append("6. For printing slot numbers for a given color").append(Constant.DASH_SEPARATOR).append(Constant.ENTER).append("slot_numbers_for_cars_with_color #car_color_string").append("\n")
                .append("7. For printing slot number for a given car number").append(Constant.DASH_SEPARATOR).append(Constant.ENTER).append("slot_number_for_registration_number #car_number").append("\n")
                .append("8: Feedback").append(Constant.DASH_SEPARATOR).append(Constant.ENTER).append("#feedback_string").append("\n\n");

        return sb.toString();
    }
}
