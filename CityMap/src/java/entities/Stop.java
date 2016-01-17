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
    private long station_id;

    @Id
    private long ride_id;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "RIDE_ID", referencedColumnName = "RIDE_ID")
    private Ride ride;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "STATION_ID", referencedColumnName = "STATION_ID")
    private Station station;

    private int halt_no;

    private int waittime;

    private int timetonextstop;

    protected Stop() {
    }

    public long getStation_id() {
        return station_id;
    }

    public void setStation_id(long station_id) {
        this.station_id = station_id;
    }

    public long getRide_id() {
        return ride_id;
    }

    public void setRide_id(long ride_id) {
        this.ride_id = ride_id;
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

    public int getHalt_no() {
        return halt_no;
    }

    public void setHalt_no(int halt_no) {
        this.halt_no = halt_no;
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
        long station_id;
        long ride_id;
    }


}
