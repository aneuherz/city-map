package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;


@Entity
@Table(schema = "CITYMAP")
public class Vehicle {
    @Id
    private int vehicleID;

    private String description;

    @OneToMany(mappedBy = "vehicle")
    private List<Line> lines;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "VEHICLE_ID"),
            inverseJoinColumns = @JoinColumn(name = "STATION_ID"))
    private List<Station> stations;

    protected Vehicle() {
    }

    public Vehicle(int vehicleID, String description) {
        this.vehicleID = vehicleID;
        this.description = description;
    }

    public void addLines(Line line) {
        this.lines.add(line);
        if (line.getVehicle() != this) {
            line.setVehicle(this);
        }
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }
}
