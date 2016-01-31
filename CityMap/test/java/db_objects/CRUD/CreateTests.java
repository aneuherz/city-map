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

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by Edi on 07/11/15.
 */
@org.junit.FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class CreateTests extends DBTests {

   @Test
   public void createDelay() throws ParseException {

       //FIXME: can create delays that are not in ride and also can't find created delay in DB, like wtf?
       userTransaction.begin();

       RideOnDay rideOnDay = rideOnDayRepository.find(1, dateForRideOnDay());

       delayRepository.create(rideOnDay, 25, "Schnee");
       userTransaction.commit();

       Delay actual = delayRepository.findByIdAndDate(1, dateForRideOnDay());
       Assert.assertNotNull(actual);
       Delay expected = new Delay(rideOnDay, 25, "Schnee");
       EntityAssert.assertEquals(expected, actual);
   }

    private Date dateForRideOnDay(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 4);
        cal.set(Calendar.MINUTE, 33);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

   @Test
   public void createLine() throws ParseException {
       userTransaction.begin();
       Vehicle vehicle = vehicleRepository.find(1);
       int id = lineRepository.create("Bus 8", vehicle).getLineID();
       userTransaction.commit();

       Line actual = lineRepository.find(id);
       Assert.assertNotNull(actual);
       Line expected = new Line(id, "Bus 8", vehicle);
       EntityAssert.assertEquals(expected, actual);
   }

   @Test
   public void createRide() throws ParseException {
       userTransaction.begin();
       Vehicle vehicle = vehicleRepository.find(1);
       RideOnDay rideOnDay = rideOnDayRepository.find(1, dateForRideOnDay());
       Line line = lineRepository.create("Bus 5", vehicle);
       RideType rideType = rideTypeRepository.create("Monday to Friday");
       int id = rideRepository.create("Neue Technik-Esperantoplatz", line, rideType, rideOnDay).getRideID();
       userTransaction.commit();

       Ride actual = rideRepository.find(id);
       Assert.assertNotNull(actual);
       Ride expected = new Ride(id, "Neue Technik-Esperantoplatz", line, rideType, rideOnDay);
       EntityAssert.assertEquals(expected, actual);
   }

   @Test
   public void createRideOnDay() {
       Date date = new Date();
       userTransaction.begin();
       Ride ride = rideRepository.find(1);
       int id = rideOnDayRepository.create(ride, date).getRide().getRideID();
       userTransaction.commit();

       RideOnDay actual = rideOnDayRepository.find(id, date);
       Assert.assertNotNull(actual);
       RideOnDay expected = new RideOnDay(ride, date);
       EntityAssert.assertEquals(expected, actual);
   }

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
