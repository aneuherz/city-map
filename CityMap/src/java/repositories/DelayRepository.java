package repositories;

import entities.Delay;
import spize.persistence.Persistence;

import java.sql.Date;

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
        Delay emp = new Delay (ride_id, ridestarttime, delayinminutes, reason);

        entityManager.persist (emp);

        return emp;
    }

    void reset ()
    {
        Persistence.resetTable    (schema, table);
        //Persistence.resetSequence (schema, sequence);
    }

    static final String schema   = "ladarphi15";
    static final String table    = "delay";
    //static final String sequence = "employee_id_seq";

}
