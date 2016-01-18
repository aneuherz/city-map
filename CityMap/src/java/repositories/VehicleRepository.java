package repositories;

import entities.Vehicle;
import spize.persistence.Persistence;

/**
 * Created by Edi on 10/01/16.
 */
public class VehicleRepository extends persistence.Repository<Vehicle>
        implements persistence.IRepository {


    public VehicleRepository() {
        super(Vehicle.class);
    }


    public Vehicle create (int vehicleID, String description)
    {
        Vehicle station = new Vehicle (vehicleID, description);

        entityManager.persist (station);

        return station;
    }

    void reset ()
    {
        Persistence.resetTable    (schema, table);
        //Persistence.resetSequence (schema, sequence);
    }

    static final String schema   = "citymap";
    static final String table    = "vehicle";
    //static final String sequence = "employee_id_seq";

}
