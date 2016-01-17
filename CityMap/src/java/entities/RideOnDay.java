package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(schema = "CITYMAP")
@IdClass(RideOnDay.RideOnDayId.class)
public class RideOnDay {
    @Id
    private int ride_id;

    @Id
    private Date ridestarttime;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "rideOnDay")
    private Delay delay;

    @ManyToOne(fetch = FetchType.LAZY)

    @PrimaryKeyJoinColumn(name="Ride_ID", referencedColumnName = "Ride_ID")
    private Ride ride;

    protected RideOnDay() {
    }

    public RideOnDay(int ride_id, Date ridestarttime) {
        this.ride_id = ride_id;
        this.ridestarttime = ridestarttime;
    }

    public int getRide_id() {
        return ride_id;
    }

    public void setRide_id(int ride_id) {
        this.ride_id = ride_id;
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

        if (!ride.getRideOnDays().contains(this)) { // warning this may cause performance issues if you have a large data set since this operation is O(n)
            ride.getRideOnDays().add(this);
        }
    }

    static class RideOnDayId implements Serializable {
        int ride_id;
        Date ridestarttime;

    }
}
