package db_objects;

import entities.Delay;
import entities.TicketType;
import entities.TicketTypeEnum;
import entities.TicketTypePrice;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.postgresql.util.PSQLException;
import repositories.DelayRepository;

import javax.persistence.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


/**
 * Created by Edi on 07/11/15.
 */
@org.junit.FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class CRUD {
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager em;
    private static EntityTransaction userTransaction;
    private static DelayRepository dr;

    @BeforeClass
    public static void setup() {


        entityManagerFactory = Persistence.createEntityManagerFactory("CityMap");
        em = entityManagerFactory.createEntityManager();
        dr = new DelayRepository();
        userTransaction = em.getTransaction();

        userTransaction.begin();
        em.createNativeQuery("SELECT citymap.teardown();").getResultList();
        userTransaction.commit();

        userTransaction.begin();
        List l = em.createNativeQuery("SELECT citymap.setup();").getResultList();
        userTransaction.commit();
    }

    @AfterClass
    public static void teardown() {
        userTransaction.begin();
        em.createNativeQuery("SELECT citymap.teardown();").getResultList();
        userTransaction.commit();
        em.close();
    }

    @Test
    public void createShouldFail() {
        boolean status = false;
        try {
            userTransaction.begin();
            TicketType ticketType = new TicketType(8, TicketTypeEnum.DOG_TICKET);
            em.persist(ticketType);
            userTransaction.commit();
        }
        catch (RollbackException ex){
            status = true;
        }
        Assert.assertEquals(true,status);
    }

    @Test
    public void create2() {
        boolean status = false;
        try {
            userTransaction.begin();
            long para = 12313;
            TicketTypePrice ticketTypePrice = new TicketTypePrice(1, new Date(0), para);
            em.persist(ticketTypePrice);
            userTransaction.commit();
        }
        catch (RollbackException ex){
            status = true;
        }
        Assert.assertEquals(true,status);
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
        Delay d = dr.create(1, testDate, 12, "Schneefall");
        em.persist(d);
        userTransaction.commit();

        List<Delay> delays = dr.findAllDelaysByLineID(3);
        Assert.assertEquals(1, delays.size());
    }
}
