package entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edi on 25/11/15.
 */
@Entity
@SequenceGenerator(name = "RideIdGenerator", schema = "CITYMAP",
        sequenceName = "RIDE_ID_SEQ", allocationSize = 1)
@Table(schema = "CITYMAP")
public class Ride {
    @Id
    @GeneratedValue(generator = "RideIdGenerator")
    @Column(name = "ride_id")
    private int rideID;

    private String description;

    @OneToMany(mappedBy = "ride", cascade = CascadeType.PERSIST)
    private List<Stop> stops;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RIDETYPE_ID")
    private RideType rideType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LINE_ID")
    private Line line;

    @OneToMany(mappedBy = "ride")
    private List<RideOnDay> rideOnDays;

    protected Ride() {
    }

    public Ride(int rideID, String description, Line line, RideType ridetype, RideOnDay rideOnDay) {
        this.rideID = rideID;
        this.description = description;
        set(line);
        set(ridetype);
        rideOnDays.add(rideOnDay);
    }

    public Ride(String description, Line line, RideType ridetype, RideOnDay rideOnDay) {
        this.description = description;
        set(line);
        set(ridetype);
        rideOnDays.add(rideOnDay);
    }

    void add(RideOnDay rideOnDay) {
        if (rideOnDays.contains(rideOnDay))
            return;

        rideOnDays.add(rideOnDay);
    }


    public int getHaltNo(Station station) throws IllegalStateException {
        for (Stop stop : stops) {
            if (station.equals(stop.getStation()))
                return stop.getHaltNo();
        }
        return 0;
    }

    public int getWaittime(Station station) throws IllegalStateException {
        for (Stop stop : stops) {
            if (station.equals(stop.getStation()))
                return stop.getWaittime();
        }
        return 0;
    }

    public int getTimetonextstop(Station station) throws IllegalStateException {
        for (Stop stop : stops) {
            if (station.equals(stop.getStation()))
                return stop.getTimetonextstop();
        }
        return 0;
    }

    public List<Station> getStations() {
        List<Station> stations = new ArrayList<Station>();

        for (Stop stop : stops) {
            Station station = stop.getStation();
            stations.add(station);
        }
        return stations;
    }

    void add(Stop stop) {
        if (stops.contains(stop))
            return;

        stops.add(stop);
    }

    void remove(RideOnDay rideOnDay) {
        rideOnDays.remove(rideOnDay);
    }

    public int getRideID() {
        return rideID;
    }

    public void setRideID(int rideID) {
        this.rideID = rideID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Stop> getStops() {
        return stops;
    }

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }

    public RideType getRideType() {
        return rideType;
    }

    public void set(RideType rideType) {
        if (this.rideType != null)
            throw new IllegalStateException("Department already set!");

        this.rideType = rideType;

        rideType.add(this);
    }

    public void changeType(RideType rideType) {
        if (this.rideType != null) {
            if (rideType.equals(this.rideType))        // no change
                return;

            this.rideType.remove(this);

            this.rideType = null;
        }

        set(rideType);
    }

    public Line getLine() {
        return line;
    }

    public void set(Line line) {
        if (this.line != null)
            throw new IllegalStateException("Department already set!");

        this.line = line;

        line.add(this);
    }

    public void changeRouteTo(Line line) {
        if (this.line != null) {
            if (line.equals(this.line))        // no change
                return;

            this.line.remove(this);

            this.line = null;
        }

        set(line);
    }

    public List<RideOnDay> getRideOnDays() {
        return rideOnDays;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Ride && ((Ride) o).rideID == rideID;
    }

}
