package repositories;

import entities.Delay;
import entities.RideOnDay;

import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

/**
 * Created by Edi on 10/01/16.
 */
public class DelayRepository extends persistence.Repository<Delay>
        implements persistence.IRepository {


    public DelayRepository() {
        super(Delay.class);
    }


    public Delay create(RideOnDay rideOnDay, int delayinminutes, String reason) {
        Delay delay = new Delay(rideOnDay, delayinminutes, reason);

        entityManager.persist(delay);
        return delay;
    }

    public Delay findByIdAndDate(int id, Date date) {
        TypedQuery<Delay> query = entityManager.createNamedQuery(
                "Delay.findByIdAndDate", Delay.class);
        query.setParameter("rideID", id);
        query.setParameter("ridestarttime", date);
        return query.getSingleResult();
    }

    public List<Delay> findAllDelaysByLineID(int lineID) {
        TypedQuery<Delay> query = entityManager.createNamedQuery(
                "Delay.findAllDelaysByLineID", Delay.class);
        query.setParameter("lineID", lineID);
        return query.getResultList();
    }

}
