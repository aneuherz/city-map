package db_objects.CRUD;

import db_objects.DBTests;
import db_objects.EntityAssert;
import entities.Delay;
import entities.Line;
import entities.Ride;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;


/**
 * Created by Edi on 07/11/15.
 */
@org.junit.FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class CreateTests extends DBTests {



    @Test
    public void createDelay() throws ParseException {
        userTransaction.begin();
        delayRepository.create(1, dayAndTimeSdf.parse("2015-01-25 17:19:00"), 25,"Schnee");
        userTransaction.commit();

        Delay actual = delayRepository.findByIdAndDate(1, dayAndTimeSdf.parse("2015-01-25 17:19:00"));
        Assert.assertNotNull(actual);
        Delay expected = new Delay(1, dayAndTimeSdf.parse("2015-01-25 17:19:00"), 25,"Schnee");
        EntityAssert.assertEntity(expected, actual);
    }

    @Test
    public void createLine() throws ParseException {
        userTransaction.begin();
        int id = lineRepository.create("Bus Blau", 3).getLineID();
        userTransaction.commit();

        Line actual = lineRepository.find(id);
        Assert.assertNotNull(actual);
        Line expected = new Line(id, "Bus Blau", 3);
        EntityAssert.assertEntity(expected, actual);
    }

    @Test
    public void createRide() throws ParseException {
        userTransaction.begin();
        int id = lineRepository.create("Bus Blau", 3).getLineID();
        userTransaction.commit();

        Ride actual = rideRepository.find(id);
        Assert.assertNotNull(actual);
        Ride expected = new Ride("Description", 3, 1);
        EntityAssert.assertEntity(expected, actual);
    }





}
