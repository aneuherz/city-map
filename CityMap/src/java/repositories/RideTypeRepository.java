package repositories;

import entities.Ride;
import entities.RideType;

/**
 * Created by Edi on 10/01/16.
 */
public class RideTypeRepository extends persistence.Repository<RideType>
        implements persistence.IRepository {


    public RideTypeRepository() {
        super(RideType.class);
    }


    public RideType create(String ridetype) {
        RideType rideType = new RideType(ridetype);

        entityManager.persist(rideType);

        return rideType;
    }

    public RideType create(int ridetypeID, String ridetype) {
        RideType rideType = new RideType(ridetypeID, ridetype);
        entityManager.persist(rideType);

        return rideType;
    }

    public void removeWithAllReferences(int rideTypeID){
        RideRepository rideRepository = new RideRepository();
        RideType rideType = entityManager.merge(find(rideTypeID));
        for (Ride ride : rideType.getRides()){
            rideRepository.removeWithAllReferences(ride.getRideID());
        }
        entityManager.remove(rideType);
    }

}
