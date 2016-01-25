package entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Edi on 25/11/15.
 */
@Entity
@Table(schema = "CITYMAP")
@IdClass(Stop.StopId.class)
public class Stop {

    @Id
    @Column(name = "station_ID")
    private long stationID;

    @Id
    @Column(name = "ride_ID")
    private long rideID;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "RIDE_ID", referencedColumnName = "RIDE_ID")
    private Ride ride;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "STATION_ID", referencedColumnName = "STATION_ID")
    private Station station;

    @Column(name = "halt_no")
    private int haltNo;

    private int waittime;

    private int timetonextstop;

    protected Stop() {
    }

    public long getStationID() {
        return stationID;
    }

    public void setStationID(long stationID) {
        this.stationID = stationID;
    }

    public long getRideID() {
        return rideID;
    }

    public void setRideID(long rideID) {
        this.rideID = rideID;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public int getHaltNo() {
        return haltNo;
    }

    public void setHaltNo(int haltNo) {
        this.haltNo = haltNo;
    }

    public int getWaittime() {
        return waittime;
    }

    public void setWaittime(int waittime) {
        this.waittime = waittime;
    }

    public int getTimetonextstop() {
        return timetonextstop;
    }

    public void setTimetonextstop(int timetonextstop) {
        this.timetonextstop = timetonextstop;
    }

    static class StopId implements Serializable {
        long stationID;
        long rideID;
    }

    @Override
    public boolean equals (Object o)
    {
        return o instanceof Stop && ((Stop) o).stationID == stationID
                && ((Stop) o).rideID == rideID;
    }

}
