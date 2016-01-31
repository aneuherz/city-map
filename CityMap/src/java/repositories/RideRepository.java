package repositories;

import entities.Line;
import entities.Ride;
import entities.RideOnDay;
import entities.RideType;

/**
 * Created by Edi on 10/01/16.
 */
public class RideRepository extends persistence.Repository<Ride>
        implements persistence.IRepository {


    public RideRepository() {
        super(Ride.class);
    }


    public Ride create(String description, Line line, RideType rideType, RideOnDay rideOnDay) {
        Ride ride = new Ride(description, line, rideType, rideOnDay);

        entityManager.persist(ride);

        return ride;
    }
}
