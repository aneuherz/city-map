package entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Edi on 25/11/15.
 */
@Entity
@Table(schema = "CITYMAP")
public class RideType {
    @Id
    @Column(name = "rideType_ID")
    private int ridetypeID;

    private String ridetype;

    @OneToMany(mappedBy = "rideType")
    private List<Ride> rides;

    protected RideType() {
    }

    public RideType(int ridetypeID, String ridetype) {
        this.ridetypeID = ridetypeID;
        this.ridetype = ridetype;
    }

    public int getRidetypeID() {
        return ridetypeID;
    }

    public void setRidetypeID(int ridetypeID) {
        this.ridetypeID = ridetypeID;
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
