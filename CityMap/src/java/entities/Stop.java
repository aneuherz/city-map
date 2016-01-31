package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Edi on 25/11/15.
 */
@Entity
@Table(schema = "CITYMAP")
@IdClass(Stop.StopId.class)
public class Stop {
    @Id
    @ManyToOne
    @JoinColumn(name = "RIDE_ID", referencedColumnName = "RIDE_ID")
    private Ride ride;

    @Id
    @ManyToOne
    @JoinColumn(name = "STATION_ID", referencedColumnName = "STATION_ID")
    private Station station;

    @Column(name = "halt_no")
    private int haltNo;

    private int waittime;

    private int timetonextstop;

    protected Stop() {
    }

    Stop(Station station, Ride ride, int haltNo, int waittime, int timetonextstop) {
        this.ride = ride;
        this.station = station;
        this.haltNo = haltNo;
        this.waittime = waittime;
        this.timetonextstop = timetonextstop;
    }

    public Ride getRide() {
        return ride;
    }

    public Station getStation() {
        return station;
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

    @Override
    public boolean equals(Object o) {
        return o instanceof Stop && ((Stop) o).getStation().getStationID() == station.getStationID()
                && ((Stop) o).getRide().getRideID() == ride.getRideID();
    }

    static class StopId implements Serializable {

        @Column(name = "ride_id")
        int ride;

        @Column(name = "station_id")
        int station;

        public StopId(int ride, int station) {
            this.ride = ride;
            this.station = station;
        }
    }

}
