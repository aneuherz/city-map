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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Edi on 25/11/15.
 */

@NamedQuery(name = "Station.findAllStationsByRideID",
        query = "SELECT s FROM Station s " +
                "JOIN s.stops st " +
                "WHERE st.ride.rideID=:rideID")
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
    private Collection<Stop> stops = new ArrayList<Stop>();

    @ManyToMany(mappedBy = "stations")
    private Collection<Vehicle> vehicles = new ArrayList<Vehicle>();


    protected Station() {
    }

    public Station(int stationID, String description) {
        this.stationID = stationID;
        this.description = description;
    }

    public Station(String description) {
        this.description = description;
    }

    void add(Vehicle vehicle) {
        if (vehicles.contains(vehicle))
            return;

        vehicles.add(vehicle);
    }

    public void add(Ride ride, int haltNo, int waittime, int timetonextstop) {
        Stop stop = new Stop(this, ride, haltNo, waittime, timetonextstop);

        if (stops.contains(stop))                 // don't set again
            return;

        stops.add(stop);
        ride.add(stop);
    }

    public int getHaltNo(Ride ride) throws IllegalStateException {
        for (Stop stop : stops) {
            if (ride.equals(stop.getRide()))
                return stop.getHaltNo();
        }
        return 0;
    }

    public int getWaittime(Ride ride) throws IllegalStateException {
        for (Stop stop : stops) {
            if (ride.equals(stop.getRide()))
                return stop.getWaittime();
        }
        return 0;
    }

    public int getTimetonextstop(Ride ride) throws IllegalStateException {
        for (Stop stop : stops) {
            if (ride.equals(stop.getRide()))
                return stop.getTimetonextstop();
        }
        return 0;
    }

    public List<Ride> getRides() {
        List<Ride> rides = new ArrayList<Ride>();

        for (Stop stop : stops) {
            Ride ride = stop.getRide();
            rides.add(ride);
        }
        return rides;
    }


    // this method is just used for testing, as it makes the assert process easier
    public void setStops(Collection<Stop> stops) {
        this.stops = stops;
    }

    // this method is just used for testing, as it makes the assert process easier
    public void setVehicles(Collection<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public Collection<Stop> getStops() {
        return stops;
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

    public Collection<Vehicle> getVehicles() {
        return vehicles;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Station && ((Station) o).stationID == stationID;
    }


}
