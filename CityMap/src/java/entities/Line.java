package entities;

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
import java.util.Collection;

/**
 * Created by Edi on 25/11/15.
 */

@Entity
@SequenceGenerator(name = "LineIdGenerator", schema = "CITYMAP",
        sequenceName = "LINE_ID_SEQ", allocationSize = 1)
@Table(schema = "CITYMAP")
public class Line {
    @Id
    @GeneratedValue(generator = "LineIdGenerator")
    @Column(name = "line_id")
    private int lineID;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VEHICLE_ID")
    private Vehicle vehicle;

    @OneToMany(mappedBy = "line")
    private Collection<Ride> rides = new ArrayList<Ride>();

    protected Line() {
    }

    public Line(int lineID, String description, Vehicle vehicle) {
        this.lineID = lineID;
        this.description = description;
        set(vehicle);
    }

    public Line(String description, Vehicle vehicle) {
        this.description = description;
        set(vehicle);
    }

    void add(Ride ride) {
        if (rides.contains(ride))
            return;

        rides.add(ride);
    }

    void remove(Ride ride) {
        rides.remove(ride);
    }

    public Collection<Ride> getRides() {
        return rides;
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

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void set(Vehicle vehicle) {

        if (this.vehicle != null)
            throw new IllegalStateException("Vehicle already set!");

        this.vehicle = vehicle;

        vehicle.add(this);
    }

    public void changeVehicle(Vehicle vehicle) {
        if (this.vehicle != null) {
            if (vehicle.equals(this.vehicle))        // no change
                return;

            this.vehicle.remove(this);

            this.vehicle = null;
        }

        set(vehicle);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Line && ((Line) o).lineID == lineID;
    }
}
