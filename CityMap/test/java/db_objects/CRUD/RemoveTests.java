package db_objects.CRUD;

import db_objects.DBTests;
import entities.Line;
import entities.Ride;
import entities.RideOnDay;
import entities.RideType;
import org.junit.Assert;
import org.junit.Test;
import spize.persistence.Transaction;

import java.text.ParseException;

/**
 * Created by Edi on 31/01/16.
 */

@org.junit.FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class RemoveTests extends DBTests {

    @Test
    public void removeLine() throws ParseException {
        Assert.assertNotNull(lineRepository.find(1));
        Transaction.begin();
        lineRepository.removeWithAllReferences(1);
        Transaction.commit();
        Line actual = lineRepository.find(1);
        Assert.assertNull(actual);
    }

    @Test
    public void removeRideTest() throws ParseException {
        Assert.assertNotNull(rideRepository.find(1));
        Transaction.begin();
        rideRepository.removeWithAllReferences(1);
        Transaction.commit();
        Ride actual = rideRepository.find(1);
        Assert.assertNull(actual);
    }

    @Test
    public void removeRideOnDay() {
        Assert.assertNotNull(rideOnDayRepository.find(1, dateForRideOnDay()));
        Transaction.begin();
        rideOnDayRepository.removeWithAllReferences(1, dateForRideOnDay());
        Transaction.commit();
        RideOnDay actual = rideOnDayRepository.find(1, dateForRideOnDay());
        Assert.assertNull(actual);
    }

    @Test
    public void removeRideType() {
        Assert.assertNotNull(rideTypeRepository.find(1));
        Transaction.begin();
        rideTypeRepository.removeWithAllReferences(1);
        Transaction.commit();
        RideType actual = rideTypeRepository.find(1);
        Assert.assertNull(actual);
    }



}
