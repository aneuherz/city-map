package db_objects;

import entities.TicketType;
import entities.TicketTypeEnum;
import entities.TicketTypePrice;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.Date;


/**
 * Created by Edi on 07/11/15.
 */
@org.junit.FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class CRUD {
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager em;
    private static EntityTransaction userTransaction;

    @BeforeClass
    public static void setup() {
        entityManagerFactory = Persistence.createEntityManagerFactory("CityMap");
        em = entityManagerFactory.createEntityManager();
        userTransaction = em.getTransaction();

    }

    @AfterClass
    public static void teardown() {

    }

    @Test
    public void create() {
        userTransaction.begin();
        TicketType ticketType = new TicketType(8, TicketTypeEnum.DOG_TICKET);
        em.persist(ticketType);
        userTransaction.commit();
    }

    @Test
    public void create2(){
        userTransaction.begin();
        long para = 12313;
        TicketTypePrice ticketTypePrice = new TicketTypePrice(1, new Date(0), para);
        em.persist(ticketTypePrice);
        userTransaction.commit();
        em.close();
        entityManagerFactory.close();
    }

    @Test
    public void find() {

    }
}
