package repositories;

import entities.RideType;
import spize.persistence.Persistence;

/**
 * Created by Edi on 10/01/16.
 */
public class RideTypeRepository extends persistence.Repository<RideType>
        implements persistence.IRepository {


    public RideTypeRepository() {
        super(RideType.class);
    }


    public RideType create (int ridetypeID, String ridetype)
    {
        RideType rideType = new RideType (ridetypeID, ridetype);

        entityManager.persist (rideType);

        return rideType;
    }

    void reset ()
    {
        Persistence.resetTable    (schema, table);
        //Persistence.resetSequence (schema, sequence);
    }

    static final String schema   = "citymap";
    static final String table    = "ridetype";
    //static final String sequence = "employee_id_seq";

}
