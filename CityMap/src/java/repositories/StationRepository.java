package repositories;

import entities.Station;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Edi on 10/01/16.
 */
public class StationRepository extends persistence.Repository<Station>
        implements persistence.IRepository {


    public StationRepository() {
        super(Station.class);
    }


    public Station create(String description) {
        Station station = new Station(description);

        entityManager.persist(station);

        return station;
    }

    public List<Station> findAllStationsByRideID(int rideID) {
        TypedQuery<Station> query = entityManager.createNamedQuery(
                "Station.findAllStationsByRideID", Station.class);
        query.setParameter("rideID", rideID);
        return query.getResultList();
    }

}
