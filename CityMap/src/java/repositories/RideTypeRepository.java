package repositories;

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

}
