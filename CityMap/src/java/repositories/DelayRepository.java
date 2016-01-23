package repositories;

import entities.Delay;
import spize.persistence.Persistence;

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


    public Delay create (int ride_id, Date ridestarttime, int delayinminutes, String reason)
    {
        Delay delay = new Delay (ride_id, ridestarttime, delayinminutes, reason);

        entityManager.persist (delay);

        return delay;
    }

    public List<Delay> findAllDelaysByLineID (int lineID)
    {
        TypedQuery<Delay> query = entityManager.createNamedQuery (
                "Delay.findAllDelaysByLineID", Delay.class );
        query.setParameter ("lineID", lineID);
        return query.getResultList();
    }

    void reset ()
    {
        Persistence.resetTable    (schema, table);
        //Persistence.resetSequence (schema, sequence);
    }

    static final String schema   = "citymap";
    static final String table    = "delay";
    //static final String sequence = "employee_id_seq";

}
