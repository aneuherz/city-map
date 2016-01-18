package repositories;

import entities.Stop;
import spize.persistence.Persistence;

/**
 * Created by Edi on 10/01/16.
 */
public class StopRepository extends persistence.Repository<Stop>
        implements persistence.IRepository {


    public StopRepository() {
        super(Stop.class);
    }

    /* TODO: keine Ahnung noch wie ein Repo mit n:m geht... noch zum nachschauen
        public Stop create (int stationID, String description)
        {

            Stop station = new Stop (stationID, description);


            entityManager.persist (station);

            return station;

        }
       */
    void reset ()
    {
        Persistence.resetTable    (schema, table);
        //Persistence.resetSequence (schema, sequence);
    }

    static final String schema   = "citymap";
    static final String table    = "stop";
    //static final String sequence = "employee_id_seq";

}
