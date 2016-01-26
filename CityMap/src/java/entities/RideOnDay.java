package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@NamedQueries({
        @NamedQuery(name = "RideOnDay.findNextRide",
                query = "SELECT rod FROM RideOnDay rod " +
                        "JOIN rod.ride ri " +
                        "JOIN ri.stops st " +
                        "WHERE st.stationID=:stationID " +
                        "AND rod.ridestarttime >= :ridestarttime " +
                        "ORDER BY rod.ridestarttime"),
        @NamedQuery(name = "RideOnDay.findAllRidesOnSpecificDateByLine",
                query = "SELECT rod " +
                        "FROM RideOnDay rod " +
                        "JOIN rod.ride r " +
                        "WHERE r.lineID=:lineID " +
                        "AND rod.ridestarttime =:ridestarttime")
})
@Entity
@Table(schema = "CITYMAP")
@IdClass(RideOnDay.RideOnDayId.class)
public class RideOnDay {
    @Id
    @Column(name = "ride_ID")
    private int rideID;

    @Id
    @Temporal(TemporalType.TIMESTAMP)
    private Date ridestarttime;


    @OneToOne(fetch = FetchType.LAZY, mappedBy = "rideOnDay")
    private Delay delay;

    @ManyToOne(fetch = FetchType.LAZY)

    @PrimaryKeyJoinColumn(name = "Ride_ID", referencedColumnName = "Ride_ID")
    private Ride ride;

    protected RideOnDay() {
    }

    public RideOnDay(int rideID, Date ridestarttime) {
        this.rideID = rideID;
        this.ridestarttime = ridestarttime;
    }

    public int getRideID() {
        return rideID;
    }

    public void setRideID(int rideID) {
        this.rideID = rideID;
    }

    public Date getRidestarttime() {
        return ridestarttime;
    }

    public void setRidestarttime(Date ridestarttime) {
        this.ridestarttime = ridestarttime;
    }

    public Delay getDelay() {
        return delay;
    }

    public void setDelay(Delay delay) {
        this.delay = delay;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;

        if (!ride.getRideOnDays().contains(this)) {
            // warning this may cause performance issues if you have a large data set since this operation is O(n)
            ride.getRideOnDays().add(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof RideOnDay && ((RideOnDay) o).rideID == rideID
                && ((RideOnDay) o).ridestarttime == ridestarttime;
    }

    static class RideOnDayId implements Serializable {
        int rideID;
        Date ridestarttime;

    }
}
