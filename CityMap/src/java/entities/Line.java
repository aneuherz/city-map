package entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Edi on 25/11/15.
 */

@Entity
@SequenceGenerator (name = "LineIdGenerator", schema = "CITYMAP",
        sequenceName = "LINE_ID_SEQ", allocationSize = 1)
@Table(schema = "CITYMAP")
public class Line {
    @Id
    @GeneratedValue (generator="LineIdGenerator")
    @Column(name = "line_id")
    private int lineID;

    private String description;

    @Column(name = "VEHICLE_ID")
    private int vehicleID;

    @ManyToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "VEHICLE_ID")
    private Vehicle vehicle;

    @OneToMany(mappedBy = "line")
    private List<Ride> rides;

    protected Line() {
    }

    public Line(int lineID, String description, int vehicleID) {
        this.lineID = lineID;
        this.description = description;
        this.vehicleID = vehicleID;
    }

    public void addRide(Ride ride) {
        this.rides.add(ride);
        if (ride.getLine() != this) {
            ride.setLine(this);
        }
    }

    public int getLineID() {
        return lineID;
    }

    public void setLineID(int lineID) {
        this.lineID = lineID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;

        if (!vehicle.getLines().contains(this)) {
            // warning this may cause performance issues if you have a large data set since this operation is O(n)
            vehicle.getLines().add(this);
        }
    }

    public List<Ride> getRides() {
        return rides;
    }

    public void setRides(List<Ride> rides) {
        this.rides = rides;
    }

    @Override
    public boolean equals (Object o)
    {
        return o instanceof Line && ((Line) o).lineID == lineID;
    }
}
