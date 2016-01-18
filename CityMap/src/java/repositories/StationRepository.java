package repositories;

import entities.Station;
import spize.persistence.Persistence;

/**
 * Created by Edi on 10/01/16.
 */
public class StationRepository extends persistence.Repository<Station>
        implements persistence.IRepository {


    public StationRepository() {
        super(Station.class);
    }


    public Station create (int stationID, String description)
    {
        Station station = new Station (stationID, description);

        entityManager.persist (station);

        return station;
    }

    void reset ()
    {
        Persistence.resetTable    (schema, table);
        //Persistence.resetSequence (schema, sequence);
    }

    static final String schema   = "citymap";
    static final String table    = "station";
    //static final String sequence = "employee_id_seq";

}
