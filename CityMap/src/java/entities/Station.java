package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by Edi on 25/11/15.
 */

@NamedQuery(name = "Station.findAllStationsByRideID",
        query = "SELECT s FROM Station s " +
                "JOIN s.stops st " +
                "WHERE st.rideID=:rideID")
@Entity
@SequenceGenerator(name = "StationIdGenerator", schema = "CITYMAP",
        sequenceName = "STATION_ID_SEQ", allocationSize = 1)
@Table(schema = "CITYMAP")
public class Station {
    @Id
    @GeneratedValue(generator = "StationIdGenerator")
    @Column(name = "station_ID")
    private int stationID;

    private String description;

    @OneToMany(mappedBy = "station")
    private List<Stop> stops;

    @ManyToMany(mappedBy = "stations")
    private List<Vehicle> vehicles;


    protected Station() {
    }

    public Station(int stationID, String description) {
        this.stationID = stationID;
        this.description = description;
    }

    public void addRide(Ride ride, int halt_no, int timetonextstop, int waittime) {
        Stop stop = new Stop();
        stop.setRide(ride);
        stop.setHaltNo(halt_no);
        stop.setRideID(ride.getRideID());
        stop.setStationID(this.getStationID());
        stop.setStation(this);
        stop.setTimetonextstop(timetonextstop);
        stop.setWaittime(waittime);

        this.stops.add(stop);
        // Also add the association object to the employee.
        ride.getStops().add(stop);
    }

    public int getStationID() {
        return stationID;
    }

    public void setStationID(int stationID) {
        this.stationID = stationID;
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

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Station && ((Station) o).stationID == stationID;
    }


}
