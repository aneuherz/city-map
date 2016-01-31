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

    public static void assertEquals(Delay expected, Delay actual) {
        Assert.assertEquals(expected.getReason(), actual.getReason());
        Assert.assertEquals(expected.getDelayinminutes(), actual.getDelayinminutes());
        Assert.assertEquals(expected.getRideOnDay(), actual.getRideOnDay());
    }

    public static void assertEquals(Line expected, Line actual) {
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getLineID(), actual.getLineID());
        Assert.assertEquals(expected.getRides(), actual.getRides());
        Assert.assertEquals(expected.getVehicle(), actual.getVehicle());
    }

    public static void assertEquals(Ride expected, Ride actual) {
        Assert.assertEquals(expected.getRideID(), actual.getRideID());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getRideOnDays(), actual.getRideOnDays());
        Assert.assertEquals(expected.getStops(), actual.getStops());
        Assert.assertEquals(expected.getRideType(), actual.getRideType());
        Assert.assertEquals(expected.getLine(), actual.getLine());

    }

    public static void assertEquals(RideOnDay expected, RideOnDay actual) {
        Assert.assertEquals(expected.getRide(), actual.getRide());
        Assert.assertEquals(expected.getRidestarttime(), actual.getRidestarttime());
        Assert.assertEquals(expected.getDelay(), actual.getDelay());
    }

    public static void assertEquals(RideType expected, RideType actual) {
        Assert.assertEquals(expected.getType(), actual.getType());
        Assert.assertEquals(expected.getRides(), actual.getRides());
        Assert.assertEquals(expected.getRidetypeID(), actual.getRidetypeID());
    }

    public static void assertEquals(Station expected, Station actual) {
        if (null != expected && null != actual) {
            if (null != actual.getStops())
                Assert.assertEquals(expected.getRides(), actual.getRides());
            Assert.assertEquals(expected.getDescription(), actual.getDescription());
            Assert.assertEquals(expected.getStationID(), actual.getStationID());
            Assert.assertEquals(expected.getVehicles(), actual.getVehicles());
        }
    }

    public static void assertEquals(Stop expected, Stop actual) {
        Assert.assertEquals(expected.getRide(), actual.getRide());
        Assert.assertEquals(expected.getStation(), actual.getStation());
        Assert.assertEquals(expected.getHaltNo(), actual.getHaltNo());
        Assert.assertEquals(expected.getTimetonextstop(), actual.getTimetonextstop());
        Assert.assertEquals(expected.getWaittime(), actual.getWaittime());
    }

    public static void assertEquals(Vehicle expected, Vehicle actual) {
        Assert.assertEquals(expected.getVehicleID(), actual.getVehicleID());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getLines(), actual.getLines());
        Assert.assertEquals(expected.getStations(), actual.getStations());
    }

}
