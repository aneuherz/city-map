package entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by Edi on 25/11/15.
 */
@Entity
@Table(schema = "LADARPHI15")
public class Ride {
    @Id
    private int ride_id;

    private String description;

    private int line_id;

    private int ridetype_id;

    @OneToMany(mappedBy = "ride")
    private List<Stop> stations;

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

    public Ride(int ride_id, String description, int line_id, int ridetype_id) {
        this.ride_id = ride_id;
        this.description = description;
        this.line_id = line_id;
        this.ridetype_id = ridetype_id;
    }

    public void addRideOnDay(RideOnDay rideOnDay) {
        this.rideOnDays.add(rideOnDay);
        if (rideOnDay.getRide() != this) {
            rideOnDay.setRide(this);
        }
    }

    public int getRide_id() {
        return ride_id;
    }

    public void setRide_id(int ride_id) {
        this.ride_id = ride_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLine_id() {
        return line_id;
    }

    public void setLine_id(int line_id) {
        this.line_id = line_id;
    }

    public int getRidetype_id() {
        return ridetype_id;
    }

    public void setRidetype_id(int ridetype_id) {
        this.ridetype_id = ridetype_id;
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

        if (!line.getRides().contains(this)) { // warning this may cause performance issues if you have a large data set since this operation is O(n)
            line.getRides().add(this);
        }
    }

    public List<RideOnDay> getRideOnDays() {
        return rideOnDays;
    }

    public void setRideOnDays(List<RideOnDay> rideOnDays) {
        this.rideOnDays = rideOnDays;
    }


}
