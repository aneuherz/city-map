package entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Edi on 25/11/15.
 */

@Entity
@SequenceGenerator (name = "StationIdGenerator", schema = "CITYMAP",
        sequenceName = "STATION_ID_SEQ", allocationSize = 1)
@Table(schema="CITYMAP")
public class Station {
    @Id
    @GeneratedValue (generator="StationIdGenerator")
    @Column(name = "station_ID")
    private int stationID;

    private String description;

    @OneToMany(mappedBy="station")
    private List<Stop> rides;

    @ManyToMany( mappedBy = "stations" )
    private List<Vehicle> vehicles;


    public void addRide(Ride ride, int halt_no, int timetonextstop, int waittime) {
        Stop stop = new Stop();
        stop.setRide(ride);
        stop.setHaltNo(halt_no);
        stop.setRideID(ride.getRideID());
        stop.setStationID(this.getStationID());
        stop.setStation(this);
        stop.setTimetonextstop(timetonextstop);
        stop.setWaittime(waittime);

        this.rides.add(stop);
        // Also add the association object to the employee.
        ride.getStations().add(stop);
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

    public List<Stop> getRides() {
        return rides;
    }

    public void setRides(List<Stop> rides) {
        this.rides = rides;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    protected Station() {
    }

    public Station(int stationID, String description) {
        this.stationID = stationID;
        this.description = description;
    }

    @Override
    public boolean equals (Object o)
    {
        return o instanceof Station && ((Station) o).stationID == stationID;
    }


}
