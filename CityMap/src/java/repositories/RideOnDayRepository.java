package repositories;

import entities.RideOnDay;
import spize.persistence.Persistence;

import java.sql.Date;

/**
 * Created by Edi on 10/01/16.
 */
public class RideOnDayRepository extends persistence.Repository<RideOnDay>
        implements persistence.IRepository {


    public RideOnDayRepository() {
        super(RideOnDay.class);
    }


    public RideOnDay create (int ride_id, Date ridestarttime)
    {
        RideOnDay rideOnDay = new RideOnDay (ride_id, ridestarttime);

        entityManager.persist (rideOnDay);

        return rideOnDay;
    }

    void reset ()
    {
        Persistence.resetTable    (schema, table);
        //Persistence.resetSequence (schema, sequence);
    }

    static final String schema   = "citymap";
    static final String table    = "rideonday";
    //static final String sequence = "employee_id_seq";

}
