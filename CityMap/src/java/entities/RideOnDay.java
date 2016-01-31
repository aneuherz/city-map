package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
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
                        "WHERE st.station.stationID=:stationID " +
                        "AND rod.ridestarttime = " +
                        "(SELECT MIN(rod.ridestarttime) FROM RideOnDay rod " +
                        "JOIN rod.ride ri " +
                        "JOIN ri.stops st " +
                        "WHERE st.station.stationID=:stationID " +
                        "AND rod.ridestarttime >= :ridestarttime) " +
                        "ORDER BY rod.ridestarttime"),
        @NamedQuery(name = "RideOnDay.findAllRidesOnSpecificDateByLine",
                query = "SELECT rod " +
                        "FROM RideOnDay rod " +
                        "JOIN rod.ride r " +
                        "WHERE r.line.lineID=:lineID " +
                        "AND rod.ridestarttime <= :dayUpperLimit " +
                        "AND rod.ridestarttime >= :dayLowerLimit")
})
@Entity
@Table(schema = "CITYMAP")
@IdClass(RideOnDay.RideOnDayId.class)
public class RideOnDay {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Ride_ID", referencedColumnName = "Ride_ID")
    private Ride ride;

    @Id
    @Temporal(TemporalType.TIMESTAMP)
    private Date ridestarttime;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "rideOnDay")
    private Delay delay;

    protected RideOnDay() {
    }

    public RideOnDay(Ride ride, Date ridestarttime) {
        this.ride = ride;
        this.ridestarttime = ridestarttime;
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

    void setDelay(Delay delay) {
        this.delay = delay;
    }

    public Ride getRide() {
        return ride;
    }

    public void set(Ride ride) {

        if (this.ride != null)
            throw new IllegalStateException("Ride already set!");

        this.ride = ride;

        ride.add(this);
    }

    public void rescheduleToDay(Ride ride) {
        if (this.ride != null) {
            if (ride.equals(this.ride))        // no change
                return;

            this.ride.remove(this);

            this.ride = null;
        }

        set(ride);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof RideOnDay && ((RideOnDay) o).ride.getRideID() == ride.getRideID()
                && ((RideOnDay) o).ridestarttime == ridestarttime;
    }

    public static class RideOnDayId implements Serializable {

        @Column(name = "ride_id")
        private int ride;
        private Date ridestarttime;

        public RideOnDayId(int ride, Date ridestarttime) {
            this.ride = ride;
            this.ridestarttime = ridestarttime;
        }
    }
}
