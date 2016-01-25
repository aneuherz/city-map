package entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Edi on 25/11/15.
 */
@Entity
@SequenceGenerator (name = "RideIdGenerator", schema = "CITYMAP",
        sequenceName = "RIDE_ID_SEQ", allocationSize = 1)
@Table(schema = "CITYMAP")
public class Ride {
    @Id
    @GeneratedValue (generator="RideIdGenerator")
    @Column(name = "ride_id")
    private int rideID;

    private String description;
    @Column(name = "line_id")
    private int lineID;
    @Column(name = "ridetype_id")
    private int rideTypeID;

    @OneToMany(mappedBy = "ride")
    private List<Stop> stations;

    @ManyToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "RIDETYPE_ID")
    private RideType rideType;

    @ManyToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "LINE_ID")
    private Line line;

    @OneToMany(mappedBy = "ride")
    private List<RideOnDay> rideOnDays;

    protected Ride() {
    }

    public Ride(int rideID, String description, int lineID, int ridetypeID) {
        this.rideID = rideID;
        this.description = description;
        this.lineID = lineID;
        this.rideTypeID = ridetypeID;
    }

    public void addRideOnDay(RideOnDay rideOnDay) {
        this.rideOnDays.add(rideOnDay);
        if (rideOnDay.getRide() != this) {
            rideOnDay.setRide(this);
        }
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

    public int getLineID() {
        return lineID;
    }

    public void setLineID(int lineID) {
        this.lineID = lineID;
    }

    public int getRideTypeID() {
        return rideTypeID;
    }

    public void setRideTypeID(int rideTypeID) {
        this.rideTypeID = rideTypeID;
    }

    public List<Stop> getStations() {
        return stations;
    }

    public void setStations(List<Stop> stations) {
        this.stations = stations;
    }

    public RideType getRideType() {
        return rideType;
    }

    public void setRideType(RideType rideType) {
        this.rideType = rideType;

        if (!rideType.getRides().contains(this)) {
            rideType.getRides().add(this);
        }
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;

        if (!line.getRides().contains(this)) {
            // warning this may cause performance issues if you have a large data set since this operation is O(n)
            line.getRides().add(this);
        }
    }

    public List<RideOnDay> getRideOnDays() {
        return rideOnDays;
    }

    public void setRideOnDays(List<RideOnDay> rideOnDays) {
        this.rideOnDays = rideOnDays;
    }

    @Override
    public boolean equals (Object o)
    {
        return o instanceof Ride && ((Ride) o).rideID == rideID;
    }

}
