package repositories;

import entities.Line;
import spize.persistence.Persistence;

/**
 * Created by Edi on 10/01/16.
 */
public class LineRepository extends persistence.Repository<Line>
        implements persistence.IRepository {


    public LineRepository() {
        super(Line.class);
    }


    public Line create (int line_id, String description, int vehicle_id)
    {
        Line emp = new Line (line_id, description, vehicle_id);

        entityManager.persist (emp);

        return emp;
    }

    void reset ()
    {
        Persistence.resetTable    (schema, table);
        //Persistence.resetSequence (schema, sequence);
    }

    static final String schema   = "ladarphi15";
    static final String table    = "line";
    //static final String sequence = "employee_id_seq";

}
