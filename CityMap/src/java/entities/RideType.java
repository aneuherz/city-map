package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Edi on 25/11/15.
 */
@Entity
@Table(schema = "CITYMAP")
@SequenceGenerator(name = "RideTypeIdGenerator", schema = "CITYMAP",
        sequenceName = "RIDETYPE_ID_SEQ", allocationSize = 1)
public class RideType {
    @Id
    @GeneratedValue(generator = "RideTypeIdGenerator")
    @Column(name = "rideType_ID")
    private int ridetypeID;

    private String ridetype;

    @OneToMany(mappedBy = "rideType")
    private Collection<Ride> rides = new ArrayList<Ride>();

    protected RideType() {
    }

    public RideType(int ridetypeID, String ridetype) {
        this.ridetypeID = ridetypeID;
        this.ridetype = ridetype;
    }

    public RideType(String ridetype) {
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

    void add(Ride ride) {
        if (rides.contains(ride))
            return;

        rides.add(ride);
    }

    void remove(Ride ride) {
        rides.remove(ride);
    }

    public Collection<Ride> getRides() {
        return rides;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof RideType && ((RideType) o).ridetypeID == ridetypeID;
    }

}
