package entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Edi on 25/11/15.
 */

@Entity
@Table(schema = "LADARPHI15")
public class Line {
    @Id
    private int line_id;

    private String description;

    private int vehicle_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VEHICLE_ID")
    private Vehicle vehicle;

    @OneToMany(mappedBy = "line")
    private List<Ride> rides;

    protected Line() {
    }

    public Line(int line_id, String description, int vehicle_id) {
        this.line_id = line_id;
        this.description = description;
        this.vehicle_id = vehicle_id;
    }

    public void addRide(Ride ride) {
        this.rides.add(ride);
        if (ride.getLine() != this) {
            ride.setLine(this);
        }
    }

    public int getLine_id() {
        return line_id;
    }

    public void setLine_id(int line_id) {
        this.line_id = line_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;

        if (!vehicle.getLines().contains(this)) { // warning this may cause performance issues if you have a large data set since this operation is O(n)
            vehicle.getLines().add(this);
        }
    }

    public List<Ride> getRides() {
        return rides;
    }

    public void setRides(List<Ride> rides) {
        this.rides = rides;
    }
}
