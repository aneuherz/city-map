package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;

@NamedQuery(name = "Vehicle.findAllVehicleByStationID",
        query = "SELECT v FROM Vehicle v " +
                "JOIN v.stations s " +
                "WHERE s.stationID=:stationID")
@Entity
@SequenceGenerator(name = "VehicleIdGenerator", schema = "CITYMAP",
        sequenceName = "VEHICLE_ID_SEQ", allocationSize = 1)
@Table(schema = "CITYMAP")
public class Vehicle {
    @Id
    @GeneratedValue(generator = "VehicleIdGenerator")
    @Column(name = "vehicle_id")
    private int vehicleID;

    private String description;

    @OneToMany(mappedBy = "vehicle")
    private Collection<Line> lines = new ArrayList<Line>();

    @ManyToMany
    @JoinTable(schema = "CITYMAP", joinColumns = @JoinColumn(name = "VEHICLE_ID"),
            inverseJoinColumns = @JoinColumn(name = "STATION_ID"))
    private Collection<Station> stations = new ArrayList<Station>();

    protected Vehicle() {
    }

    public Vehicle(int vehicleID, String description) {
        this.vehicleID = vehicleID;
        this.description = description;
    }

    public Vehicle(String description) {
        this.description = description;
    }

    void add(Line line) {
        if (lines.contains(line))
            return;

        lines.add(line);
    }

    void remove(Line line) {
        lines.remove(line);
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public void add(Station station) {
        if (stations.contains(station))
            return;

        stations.add(station);

        station.add(this);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Collection<Line> getLines() {
        return lines;
    }

    public Collection<Station> getStations() {
        return stations;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Vehicle && ((Vehicle) o).vehicleID == vehicleID;
    }
}
