package db_objects;

import org.junit.BeforeClass;
import repositories.DelayRepository;
import repositories.LineRepository;
import repositories.RideOnDayRepository;
import repositories.RideRepository;
import repositories.RideTypeRepository;
import repositories.StationRepository;
import repositories.VehicleRepository;
import spize.persistence.Persistence;
import spize.persistence.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Alexander on 24.01.2016.
 */
public abstract class DBTests {
    private static String persistenceUnit = "CityMap";
    private static String user = "citymap_user";
    private static String password = "citymap_user";
    protected static EntityManager em;
    protected static EntityTransaction userTransaction;
    protected static DelayRepository delayRepository;
    protected static LineRepository lineRepository;
    protected static RideRepository rideRepository;
    protected static RideOnDayRepository rideOnDayRepository;
    protected static RideTypeRepository rideTypeRepository;
    protected static StationRepository stationRepository;
    protected static VehicleRepository vehicleRepository;
    protected static SimpleDateFormat dayAndTimeSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @BeforeClass
    public static void setup() {
        Persistence.connect(persistenceUnit, user, password);
        em= Persistence.getEntityManager();
        delayRepository = new DelayRepository();
        lineRepository = new LineRepository();
        rideRepository = new RideRepository();
        rideOnDayRepository = new RideOnDayRepository();
        rideTypeRepository = new RideTypeRepository();
        stationRepository = new StationRepository();
        vehicleRepository = new VehicleRepository();
        userTransaction = em.getTransaction();

        teardown();

        Transaction.begin();
        List l = em.createNativeQuery("SELECT citymap.setup();").getResultList();
        Transaction.commit();
    }

    public static void teardown() {
        Transaction.begin();
        em.createNativeQuery("SELECT citymap.teardown();").getResultList();
        Transaction.commit();
    }

    public Date dateForRideOnDay(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 4);
        cal.set(Calendar.MINUTE, 33);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

}
