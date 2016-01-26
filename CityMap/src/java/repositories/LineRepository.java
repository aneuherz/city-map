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


    public Line create (String description, int vehicle_id)
    {
        Line line = new Line (description, vehicle_id);

        entityManager.persist (line);

        return line;
    }

    public Line find (int id){
        return entityManager.find(Line.class, id);
    }

    void reset ()
    {
        Persistence.resetTable    (schema, table);
        Persistence.resetSequence (schema, sequence);
    }

    static final String schema   = "citymap";
    static final String table    = "line";
    static final String sequence = "LINE_ID_SEQ";

}
