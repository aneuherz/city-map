package repositories;

import entities.Ride;
import spize.persistence.Persistence;

/**
 * Created by Edi on 10/01/16.
 */
public class RideRepository extends persistence.Repository<Ride>
        implements persistence.IRepository {


    public RideRepository() {
        super(Ride.class);
    }


    public Ride create (int ride_id, String description, int line_id, int ridetype_id)
    {
        Ride ride = new Ride (ride_id, description, line_id, ridetype_id);

        entityManager.persist (ride);

        return ride;
    }

    void reset ()
    {
        Persistence.resetTable    (schema, table);
        //Persistence.resetSequence (schema, sequence);
    }

    static final String schema   = "citymap";
    static final String table    = "ride";
    //static final String sequence = "employee_id_seq";

}
