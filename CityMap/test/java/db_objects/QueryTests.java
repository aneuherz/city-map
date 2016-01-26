package db_objects;

import entities.Delay;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Calendar;
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
        String date = dayAndTimeSdf.format(cal.getTime());

        Timestamp testDate = Timestamp.valueOf(date);
        Delay d = delayRepository.create(1, testDate, 12, "Schneefall");
        em.persist(d);
        userTransaction.commit();

        List<Delay> delays = delayRepository.findAllDelaysByLineID(3);
        Assert.assertEquals(1, delays.size());
    }


    @Test
    public void findAllStationsByRideID() {

    }

}
