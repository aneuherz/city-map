package db_objects;

import entities.Delay;
import entities.Line;
import entities.Ride;
import entities.RideOnDay;
import entities.RideType;
import entities.Station;
import entities.Stop;
import entities.Vehicle;
import org.junit.Assert;

/**
 * Created by Edi on 25/01/16.
 */
public class EntityAssert {

    public static void assertDelay(Delay expected, Delay actual) {
        Assert.assertEquals(expected.getReason(), actual.getReason());
        Assert.assertEquals(expected.getDelayinminutes(), actual.getDelayinminutes());
        Assert.assertEquals(expected.getRideID(), actual.getRideID());
        Assert.assertEquals(expected.getRideOnDay(), actual.getRideOnDay());
        Assert.assertEquals(expected.getRidestarttime(), actual.getRidestarttime());
    }

    public static void asserLine(Line expected, Line actual) {
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getLineID(), actual.getLineID());
        Assert.assertEquals(expected.getRides(), actual.getRides());
        Assert.assertEquals(expected.getVehicle(), actual.getVehicle());
        Assert.assertEquals(expected.getVehicleID(), actual.getVehicleID());
    }

    public static void assertRide(Ride expected, Ride actual) {
        Assert.assertEquals(expected.getRideID(), actual.getRideID());
        Assert.assertEquals(expected.getLineID(), actual.getLineID());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getRideOnDays(), actual.getRideOnDays());
        Assert.assertEquals(expected.getStations(), actual.getStations());
        Assert.assertEquals(expected.getRideType(), actual.getRideType());
        Assert.assertEquals(expected.getLine(), actual.getLine());

    }

    public static void assertRideOnDay(RideOnDay expected, RideOnDay actual) {
        Assert.assertEquals(expected.getRideID(), actual.getRideID());
        Assert.assertEquals(expected.getRide(), actual.getRide());
        Assert.assertEquals(expected.getRidestarttime(), actual.getRidestarttime());
        Assert.assertEquals(expected.getDelay(), actual.getDelay());
    }

    public static void assertRideType(RideType expected, RideType actual) {
        Assert.assertEquals(expected.getRidetype(), actual.getRidetype());
        Assert.assertEquals(expected.getRides(), actual.getRides());
        Assert.assertEquals(expected.getRidetypeID(), actual.getRidetypeID());
    }

    public static void assertStation(Station expected, Station actual) {
        Assert.assertEquals(expected.getRides(), actual.getRides());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getStationID(), actual.getStationID());
        Assert.assertEquals(expected.getVehicles(), actual.getVehicles());
    }

    public static void assertStop(Stop expected, Stop actual) {
        Assert.assertEquals(expected.getRideID(), actual.getRideID());
        Assert.assertEquals(expected.getRide(), actual.getRide());
        Assert.assertEquals(expected.getStationID(), actual.getStationID());
        Assert.assertEquals(expected.getStation(), actual.getStation());
        Assert.assertEquals(expected.getHaltNo(), actual.getHaltNo());
        Assert.assertEquals(expected.getTimetonextstop(), actual.getTimetonextstop());
        Assert.assertEquals(expected.getWaittime(), actual.getWaittime());
    }

    public static void assertVehicle(Vehicle expected, Vehicle actual) {
        Assert.assertEquals(expected.getVehicleID(), actual.getVehicleID());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getLines(), actual.getLines());
        Assert.assertEquals(expected.getStations(), actual.getStations());
    }

}
