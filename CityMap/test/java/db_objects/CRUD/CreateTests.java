package db_objects.CRUD;

import db_objects.DBTests;
import db_objects.EntityAssert;
import entities.RideType;
import entities.Station;
import entities.Vehicle;
import org.junit.Assert;
import org.junit.Test;


/**
 * Created by Edi on 07/11/15.
 */
@org.junit.FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class CreateTests extends DBTests {
//
//    @Test
//    public void createDelay() throws ParseException {
//
//        //FIXME: can create delays that are not in ride and also can't find created delay in DB, like wtf?
//        userTransaction.begin();
//        delayRepository.create(1, dayAndTimeSdf.parse("2015-01-25 17:19:00"), 25, "Schnee");
//        userTransaction.commit();
//
//        Delay actual = delayRepository.findByIdAndDate(1, dayAndTimeSdf.parse("2015-01-25 17:19:00"));
//        Assert.assertNotNull(actual);
//        Delay expected = new Delay(1, dayAndTimeSdf.parse("2015-01-25 17:19:00"), 25, "Schnee");
//        EntityAssert.assertEquals(expected, actual);
//    }
//
//    @Test
//    public void createLine() throws ParseException {
//        userTransaction.begin();
//        int id = lineRepository.create("Bus 5", 3).getLineID();
//        userTransaction.commit();
//
//        Line actual = lineRepository.find(id);
//        Assert.assertNotNull(actual);
//        Line expected = new Line(id, "Bus 5", 3);
//        EntityAssert.assertEquals(expected, actual);
//    }
//
//    @Test
//    public void createRide() throws ParseException {
//        userTransaction.begin();
//        Line line = lineRepository.create("Bus 5", 3);
//        RideType rideType = rideTypeRepository.create("Monday to Friday");
//        int id = rideRepository.create("Neue Technik-Esperantoplatz", line, rideType).getRideID();
//        userTransaction.commit();
//
//        Ride actual = rideRepository.find(id);
//        Assert.assertNotNull(actual);
//        Ride expected = new Ride(id, "Neue Technik-Esperantoplatz", line, rideType);
//        EntityAssert.assertEquals(expected, actual);
//    }
//
//    @Test
//    public void createRideOnDay() {
//        Date date = new Date();
//        userTransaction.begin();
//        int id = rideOnDayRepository.create(99, date).getRideID();
//        userTransaction.commit();
//
//        RideOnDay actual = rideOnDayRepository.find(id, date);
//        Assert.assertNotNull(actual);
//        RideOnDay expected = new RideOnDay(id, date);
//        EntityAssert.assertEquals(expected, actual);
//    }

    @Test
    public void createRideType() {
        userTransaction.begin();
        int id = rideTypeRepository.create("Monday to Friday").getRidetypeID();
        userTransaction.commit();

        RideType actual = rideTypeRepository.find(id);
        Assert.assertNotNull(actual);
        RideType expected = new RideType(id, "Monday to Friday");
        EntityAssert.assertEquals(expected, actual);
    }

    @Test
    public void createStation() {
        userTransaction.begin();
        int id = stationRepository.create("Jakominiplatz").getStationID();
        userTransaction.commit();

        Station actual = stationRepository.find(id);
        Assert.assertNotNull(actual);
        Station expected = new Station(id, "Jakominiplatz");
        EntityAssert.assertEquals(expected, actual);
    }

    @Test
    public void createStop() {
//        userTransaction.begin();
//        int id = stopRepository.create("Jakominiplatz").getStationID();
//        userTransaction.commit();
//
//        Station actual = stationRepository.find(id);
//        Assert.assertNotNull(actual);
//        Station expected = new Station(id ,"Jakominiplatz");
//        EntityAssert.assertEquals(expected, actual);
    }

    @Test
    public void createVehicle() {
        userTransaction.begin();
        int id = vehicleRepository.create("bus").getVehicleID();
        userTransaction.commit();

        Vehicle actual = vehicleRepository.find(id);
        Assert.assertNotNull(actual);
        Vehicle expected = new Vehicle(id, "bus");
        EntityAssert.assertEquals(expected, actual);
    }

}
