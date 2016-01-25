package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(schema = "CITYMAP")
@IdClass(RideOnDay.RideOnDayId.class)
public class RideOnDay {
    @Id
    @Column(name = "ride_ID")
    private int rideID;

    @Id
    @Temporal ( TemporalType.TIMESTAMP )
    private Date ridestarttime;


    @OneToOne(fetch = FetchType.LAZY, mappedBy = "rideOnDay")
    private Delay delay;

    @ManyToOne(fetch = FetchType.LAZY)

    @PrimaryKeyJoinColumn(name="Ride_ID", referencedColumnName = "Ride_ID")
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

    static class RideOnDayId implements Serializable {
        int rideID;
        Date ridestarttime;

    }

    @Override
    public boolean equals (Object o)
    {
        return o instanceof RideOnDay && ((RideOnDay) o).rideID == rideID
                && ((RideOnDay) o).ridestarttime == ridestarttime;
    }
}
