package db_objects;

import entities.Delay;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


/**
 * Created by Edi on 07/11/15.
 */
@org.junit.FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class CRUD extends DBTests {



    @Test
    public void create() throws ParseException {
        delayRepository.create(1, dayAndTimeSdf.parse("2015-01-25 17:19:00"), 25,"Schnee");
        Delay actual = delayRepository.find(1);
        Delay expected = new Delay(1, dayAndTimeSdf.parse("2015-01-25 17:19:00"), 25,"Schnee");
        EntityAssert.assertDelay(expected, actual);
    }

    @Test
    public void find() {
        userTransaction.begin();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY,4);
        cal.set(Calendar.MINUTE,33);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = df.format(cal.getTime());

        Timestamp testDate = Timestamp.valueOf(date);
        Delay d = delayRepository.create(1, testDate, 12, "Schneefall");
        em.persist(d);
        userTransaction.commit();

        List<Delay> delays = delayRepository.findAllDelaysByLineID(3);
        Assert.assertEquals(1, delays.size());
    }
}
