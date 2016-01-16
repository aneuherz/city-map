package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by Edi on 25/11/15.
 */
@Entity
@Table(schema = "LADARPHI15")
public class RideType {
    @Id
    private int ridetype_id;

    private String ridetype;

    @OneToMany(mappedBy = "rideType")
    private List<Ride> rides;

    protected RideType() {
    }

    public RideType(int ridetype_id, String ridetype) {
        this.ridetype_id = ridetype_id;
        this.ridetype = ridetype;
    }

    public int getRidetype_id() {
        return ridetype_id;
    }

    public void setRidetype_id(int ridetype_id) {
        this.ridetype_id = ridetype_id;
    }

    public String getRidetype() {
        return ridetype;
    }

    public void setRidetype(String ridetype) {
        this.ridetype = ridetype;
    }

    public void addRide(Ride ride) {
        this.rides.add(ride);
        if (ride.getRideType() != this) {
            ride.setRideType(this);
        }
    }

    public List<Ride> getRides() {
        return rides;
    }

    public void setRides(List<Ride> rides) {
        this.rides = rides;
    }


}
