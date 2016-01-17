package entities;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(schema = "CITYMAP")
public class Vehicle {
    @Id
    private int vehicle_id;

    private String description;

    @OneToMany(mappedBy = "vehicle")
    private List<Line> lines;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "VEHICLE_ID")
            , inverseJoinColumns = @JoinColumn(name = "STATION_ID"))
    private List<Station> stations;

    protected Vehicle() {
    }

    public Vehicle(int vehicle_id, String description) {
        this.vehicle_id = vehicle_id;
        this.description = description;
    }

    public void addLines(Line line) {
        this.lines.add(line);
        if (line.getVehicle() != this) {
            line.setVehicle(this);
        }
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
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
