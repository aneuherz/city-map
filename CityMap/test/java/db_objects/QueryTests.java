package db_objects;

import entities.Delay;
import entities.RideOnDay;
import entities.Station;
import entities.Vehicle;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Edi on 25/01/16.
 */
public class QueryTests extends DBTests {

    @Test
    public void findAllDelaysByLineID() {
        userTransaction.begin();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 4);
        cal.set(Calendar.MINUTE, 33);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        RideOnDay rideOnDay = rideOnDayRepository.find(1);

        Delay d = delayRepository.create(rideOnDay, 12, "Schneefall");
        em.persist(d);
        userTransaction.commit();

        List<Delay> delays = delayRepository.findAllDelaysByLineID(3);
        Assert.assertEquals(1, delays.size());
    }


    @Test
    public void findAllStationsByRideID() {
        List<Station> stationLine = stationRepository.findAllStationsByRideID(1);
        Assert.assertEquals(7, stationLine.size());
    }

    @Test
    public void findAllVehicleByStation() {
        List<Vehicle> vehiclesByStation = vehicleRepository.findAllVehicleByStationID(1);
        Assert.assertEquals(2, vehiclesByStation.size());
    }

    @Test
    public void findNextRide() {
        Date date = new Date();

        List<RideOnDay> nextRideOnDay = rideOnDayRepository.findNextRide(1, date);

    }

    @Test
    public void findAllRidesOnSpecificDateByLine() {
        Date date = new Date();

        List<RideOnDay> nextRideOnDay = rideOnDayRepository.findAllRidesOnSpecificDateByLine(3, date);
    }

}
