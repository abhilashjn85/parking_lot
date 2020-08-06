package com.gojek.parking;

import com.gojek.parking.api.ParkingLotWrapper;
import com.gojek.parking.constant.Constant;
import com.gojek.parking.data.ParkingTestData;
import com.gojek.parking.exception.NoParkingLotException;
import com.gojek.parking.exception.ParkingLotException;
import com.gojek.parking.processor.BaseProcessor;
import com.gojek.parking.processor.ParkingLotProcessor;
import com.gojek.parking.service.impl.IssueSlotServiceImpl;
import com.gojek.parking.service.impl.ParkingLotServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.assertTrue;

public class MainTest {

    BaseProcessor processor = new ParkingLotProcessor();
    private final ByteArrayOutputStream	outContent	= new ByteArrayOutputStream();

    @Before
    public void init() {
        processor.setParkingLotService(new ParkingLotServiceImpl());
        processor.setIssueSlotService(new IssueSlotServiceImpl());
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void createParkingLot() throws Exception {

        ParkingLotWrapper wrapper = ParkingTestData.dataParkingLotWithCapacity(6);
        processor.process(
                wrapper.getParkingLot(),
                wrapper.getParkingLotIntent());

        assertTrue("Created a parking lot with 6 slots".equalsIgnoreCase(outContent.toString().replace("\n", "")));

        IssueSlotServiceImpl.getInstance(6).refresh();
    }

    @Test (expected = NoParkingLotException.class)
    public void parkWithoutCreatingParkingLot() {

        ParkingLotWrapper wrapper = ParkingTestData.dataParkingLotForPark("KA-01-HH-1234", "White");
        processor.process(
                wrapper.getParkingLot(),
                wrapper.getParkingLotIntent());
    }

    @Test (expected = NoParkingLotException.class)
    public void unparkWithoutCreatingParkingLot() {
        ParkingLotWrapper wrapper = ParkingTestData.dataParkingLotForUnPark(1);
        processor.process(
                wrapper.getParkingLot(),
                wrapper.getParkingLotIntent());

        // refresh data
        IssueSlotServiceImpl.getInstance(1).refresh();
    }


    @Test
    public void parkWithCapacityOverLimit() {

        // create parking lot with capacity 1
        ParkingLotWrapper createData = ParkingTestData.dataParkingLotWithCapacity(1);
        ParkingLotServiceImpl parkingLot = new ParkingLotServiceImpl();
        parkingLot.createParkingLot(createData.getParkingLot());

        // park 1st car
        ParkingLotWrapper parkData1 = ParkingTestData.dataParkingLotForPark("KA-01-HH-1234", "White");
        parkingLot.park(parkData1.getParkingLot().getVehicle());

        // park 2nd car, it can not be because capacity limit is reached
        ParkingLotWrapper parkData2 = ParkingTestData.dataParkingLotForPark("KA-01-HH-9999", "White");
        int parkCode = parkingLot.park(parkData2.getParkingLot().getVehicle());

        assertTrue(parkCode == Constant.NOT_ENOUGH_SPACE_CODE);

        // refresh data
        IssueSlotServiceImpl.getInstance(1).refresh();
    }

    @Test
    public void parkWithVehicleAlreadyParked() {

        // create parking lot with capacity 2
        ParkingLotWrapper createData = ParkingTestData.dataParkingLotWithCapacity(2);
        ParkingLotServiceImpl parkingLot = new ParkingLotServiceImpl();
        parkingLot.createParkingLot(createData.getParkingLot());

        // park 1st car
        ParkingLotWrapper parkData1 = ParkingTestData.dataParkingLotForPark("KA-01-HH-1234", "White");
        parkingLot.park(parkData1.getParkingLot().getVehicle());

        // park 2nd car of same registration number
        ParkingLotWrapper parkData2 = ParkingTestData.dataParkingLotForPark("KA-01-HH-1234", "White");
        int parkCode = parkingLot.park(parkData2.getParkingLot().getVehicle());

        assertTrue(parkCode == Constant.ALREADY_PARKED_CODE);

        // refresh data
        IssueSlotServiceImpl.getInstance(2).refresh();
    }


    @Test
    public void slotForRegistrationNumber() {

        ParkingLotWrapper createData = ParkingTestData.dataParkingLotWithCapacity(2);
        ParkingLotServiceImpl parkingLot = new ParkingLotServiceImpl();
        parkingLot.createParkingLot(createData.getParkingLot());

        // park 1st car
        ParkingLotWrapper parkData1 = ParkingTestData.dataParkingLotForPark("KA-01-HH-1234", "White");
        parkingLot.park(parkData1.getParkingLot().getVehicle());

        // park 2nd car
        ParkingLotWrapper parkData2 = ParkingTestData.dataParkingLotForPark("KA-01-HH-9999", "White");
        parkingLot.park(parkData2.getParkingLot().getVehicle());

        int slotNumber = parkingLot.fetchSlotNumberByRegistrationNumber("KA-01-HH-9999");
        assertTrue(slotNumber == 2);

        // refresh data
        IssueSlotServiceImpl.getInstance(2).refresh();
    }


    @Test (expected = ParkingLotException.class)
    public void slotForRegistrationNumberNotFound() {

        ParkingLotWrapper createData = ParkingTestData.dataParkingLotWithCapacity(2);
        ParkingLotServiceImpl parkingLot = new ParkingLotServiceImpl();
        parkingLot.createParkingLot(createData.getParkingLot());

        // park 1st car
        ParkingLotWrapper parkData1 = ParkingTestData.dataParkingLotForPark("KA-01-HH-1234", "White");
        parkingLot.park(parkData1.getParkingLot().getVehicle());

        // fetching slot number with undefined car
        parkingLot.fetchSlotNumberByRegistrationNumber("KA-01-HH-9999");

        new IssueSlotServiceImpl().refresh();

        // refresh data
        IssueSlotServiceImpl.getInstance(2).refresh();
    }
}
