package db_objects;

import org.junit.BeforeClass;
import repositories.DelayRepository;
import repositories.LineRepository;
import repositories.RideOnDayRepository;
import repositories.RideRepository;
import repositories.RideTypeRepository;
import repositories.StationRepository;
import repositories.VehicleRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Alexander on 24.01.2016.
 */
public abstract class DBTests {
    protected static EntityManagerFactory entityManagerFactory;
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
        entityManagerFactory = Persistence.createEntityManagerFactory("CityMap");
        em = entityManagerFactory.createEntityManager();
        delayRepository = new DelayRepository();
        lineRepository = new LineRepository();
        rideRepository = new RideRepository();
        rideOnDayRepository = new RideOnDayRepository();
        rideTypeRepository = new RideTypeRepository();
        stationRepository = new StationRepository();
        vehicleRepository = new VehicleRepository();
        userTransaction = em.getTransaction();

        teardown();

        userTransaction.begin();
        List l = em.createNativeQuery("SELECT citymap.setup();").getResultList();
        userTransaction.commit();

        em.close();
    }

    public static void teardown(){
        userTransaction.begin();
        em.createNativeQuery("SELECT citymap.teardown();").getResultList();
        userTransaction.commit();
    }
}
