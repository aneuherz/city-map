package repositories;

import entities.Vehicle;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Edi on 10/01/16.
 */
public class VehicleRepository extends persistence.Repository<Vehicle>
        implements persistence.IRepository {

    public VehicleRepository() {
        super(Vehicle.class);
    }

    public Vehicle create(String description) {
        Vehicle station = new Vehicle(description);

        entityManager.persist(station);

        return station;
    }

    public List<Vehicle> findAllVehicleByStationID(int stationID) {
        TypedQuery<Vehicle> query = entityManager.createNamedQuery(
                "Vehicle.findAllVehicleByStationID", Vehicle.class);
        query.setParameter("stationID", stationID);
        return query.getResultList();
    }
}
