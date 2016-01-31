package repositories;

import entities.Line;
import entities.Vehicle;

/**
 * Created by Edi on 10/01/16.
 */
public class LineRepository extends persistence.Repository<Line>
        implements persistence.IRepository {


    public LineRepository() {
        super(Line.class);
    }


    public Line create(String description, Vehicle vehicle) {
        Line line = new Line(description, vehicle);

        entityManager.persist(line);
        return line;
    }

    public void removeWithAllReferences(int lineID) {
        Line line = entityManager.merge(find(lineID));
        entityManager.remove(line);
    }

    public Line find(int id) {
        return entityManager.find(Line.class, id);
    }

}
