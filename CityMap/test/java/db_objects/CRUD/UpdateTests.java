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

/**
 * Created by Edi on 31/01/16.
 */
public class UpdateTests extends DBTests {

    @Test
    public void updateDelay() throws ParseException {
        Transaction.begin();
        RideOnDay rideOnDay = rideOnDayRepository.find(1, dateForRideOnDay());
        delayRepository.create(rideOnDay, 25, "Schnee").setDelayinminutes(30);
        Transaction.commit();

        Delay actual = delayRepository.findByIdAndDate(1, dateForRideOnDay());
        Assert.assertNotNull(actual);
        Delay expected = new Delay(rideOnDay, 30, "Schnee");
        EntityAssert.assertEquals(expected, actual);
    }

    @Test
    public void updateLine() throws ParseException {
        Transaction.begin();
        Vehicle vehicle = vehicleRepository.create("taxi");

        Line line = lineRepository.find(1);
        line.changeVehicle(vehicle);
        Transaction.commit();

        Line actual = lineRepository.find(1);
        Assert.assertNotNull(actual);
        Line expected = new Line(1, "Tram 1", vehicle);
        EntityAssert.assertEquals(expected, actual);
    }

    @Test
    public void updateRide() throws ParseException {
        Transaction.begin();
        Line line = lineRepository.find(2);
        Ride ride = rideRepository.find(2);
        ride.changeRouteTo(line);

        // new find to see if not only the object changed but also the DB entity
        Ride actual = rideRepository.find(2);
        Assert.assertNotNull(actual);

        // this is done outside of the transaction so that this will not change the DB-Entities
        Ride expected = new Ride(2, "Neue Technik-Esperantoplatz", line, ride.getRideType());
        expected.setRideOnDays(ride.getRideOnDays());
        expected.setStops(ride.getStops());
        Transaction.commit();

        EntityAssert.assertEquals(expected, actual);
    }

    @Test
    public void updateRideType() {
        Transaction.begin();
        RideType rideType = rideTypeRepository.find(1);
        rideType.setType("Updatetest");
        Transaction.commit();

        RideType actual = rideTypeRepository.find(1);
        Assert.assertNotNull(actual);
        RideType expected = new RideType(1, "Updatetest");
        expected.setRides(rideType.getRides());
        EntityAssert.assertEquals(expected, actual);
    }

    @Test
    public void updateStation() {
        Transaction.begin();
        Station station = stationRepository.find(1);
        station.setDescription("Updatetest");
        Transaction.commit();

        Station actual = stationRepository.find(station.getStationID());
        Assert.assertNotNull(actual);
        Station expected = new Station(station.getStationID(), "Updatetest");
        expected.setStops(station.getStops());
        expected.setVehicles(station.getVehicles());

        EntityAssert.assertEquals(expected, actual);
    }

    @Test
    public void updateVehicle() {
        Transaction.begin();
        Vehicle vehicle = vehicleRepository.find(1);
        vehicle.setDescription("updatetest");
        Transaction.commit();

        Vehicle actual = vehicleRepository.find(1);
        Assert.assertNotNull(actual);
        Vehicle expected = new Vehicle(1, "updatetest");
        expected.setLines(vehicle.getLines());
        expected.setStations(vehicle.getStations());

        EntityAssert.assertEquals(expected, actual);
    }

}
