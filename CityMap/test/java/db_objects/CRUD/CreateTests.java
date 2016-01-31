package db_objects.CRUD;

import db_objects.DBTests;
import db_objects.EntityAssert;
import entities.Delay;
import entities.Line;
import entities.Ride;
import entities.RideOnDay;
import entities.RideType;
import entities.Station;
import entities.Vehicle;
import org.junit.Assert;
import org.junit.Test;
import spize.persistence.Transaction;

import java.text.ParseException;
import java.util.Date;


/**
 * Created by Edi on 07/11/15.
 */
@org.junit.FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class CreateTests extends DBTests {

   @Test
   public void createDelay() throws ParseException {
       Transaction.begin();

       RideOnDay rideOnDay = rideOnDayRepository.find(1, dateForRideOnDay());

       delayRepository.create(rideOnDay, 25, "Schnee");
       Transaction.commit();

       Delay actual = delayRepository.findByIdAndDate(1, dateForRideOnDay());
       Assert.assertNotNull(actual);
       Delay expected = new Delay(rideOnDay, 25, "Schnee");
       EntityAssert.assertEquals(expected, actual);
   }

   @Test
   public void createLine() throws ParseException {
       Transaction.begin();
       Vehicle vehicle = vehicleRepository.find(1);
       int id = lineRepository.create("Bus 8", vehicle).getLineID();
       Transaction.commit();

       Line actual = lineRepository.find(id);
       Assert.assertNotNull(actual);
       Line expected = new Line(id, "Bus 8", vehicle);
       EntityAssert.assertEquals(expected, actual);
   }

   @Test
   public void createRide() throws ParseException {
       Transaction.begin();
       Vehicle vehicle = vehicleRepository.find(1);
       RideOnDay rideOnDay = rideOnDayRepository.find(1, dateForRideOnDay());
       Line line = lineRepository.create("Bus 5", vehicle);
       RideType rideType = rideTypeRepository.create("Monday to Friday");
       int id = rideRepository.create("Neue Technik-Esperantoplatz", line, rideType, rideOnDay).getRideID();
       Transaction.commit();

       Ride actual = rideRepository.find(id);
       Assert.assertNotNull(actual);
       Ride expected = new Ride(id, "Neue Technik-Esperantoplatz", line, rideType, rideOnDay);
       EntityAssert.assertEquals(expected, actual);
   }

   @Test
   public void createRideOnDay() {
       Date date = new Date();
       Transaction.begin();
       Ride ride = rideRepository.find(1);
       int id = rideOnDayRepository.create(ride, date).getRide().getRideID();
       Transaction.commit();

       RideOnDay actual = rideOnDayRepository.find(id, date);
       Assert.assertNotNull(actual);
       RideOnDay expected = new RideOnDay(ride, date);
       EntityAssert.assertEquals(expected, actual);
   }

    @Test
    public void createRideType() {
        Transaction.begin();
        int id = rideTypeRepository.create("Monday to Friday").getRidetypeID();
        Transaction.commit();

        RideType actual = rideTypeRepository.find(id);
        Assert.assertNotNull(actual);
        RideType expected = new RideType(id, "Monday to Friday");
        EntityAssert.assertEquals(expected, actual);
    }

    @Test
    public void createStation() {
        Transaction.begin();
        Station station = stationRepository.create("Jakominiplatz");
        int id = station.getStationID();
        Ride ride = rideRepository.find(1);
        station.add(ride, 99, 10, 20);
        Transaction.commit();

        Station actual = stationRepository.find(id);
        Assert.assertNotNull(actual);
        Station expected = new Station(id, "Jakominiplatz");
        expected.add(ride, 99, 10, 20);
        EntityAssert.assertEquals(expected, actual);
    }

    @Test
    public void createVehicle() {
        Transaction.begin();
        int id = vehicleRepository.create("bus").getVehicleID();
        Transaction.commit();

        Vehicle actual = vehicleRepository.find(id);
        Assert.assertNotNull(actual);
        Vehicle expected = new Vehicle(id, "bus");
        EntityAssert.assertEquals(expected, actual);
    }

}
