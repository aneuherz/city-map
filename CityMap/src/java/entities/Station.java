package entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Edi on 25/11/15.
 */

@Entity
@Table(schema="LADARPHI15")
public class Station {
    @Id
    private int station_id;

    private String description;

    @OneToMany(mappedBy="station")
    private List<Stop> rides;

    @ManyToMany( mappedBy = "stations" )
    private List<Vehicle> vehicles;


    public void addRide(Ride ride, int halt_no, int timetonextstop, int waittime) {
        Stop stop = new Stop();
        stop.setRide(ride);
        stop.setHalt_no(halt_no);
        stop.setRide_id(ride.getRide_id());
        stop.setStation_id(this.getStation_id());
        stop.setStation(this);
        stop.setTimetonextstop(timetonextstop);
        stop.setWaittime(waittime);

        this.rides.add(stop);
        // Also add the association object to the employee.
        ride.getStations().add(stop);
    }

    public int getStation_id() {
        return station_id;
    }

    public void setStation_id(int station_id) {
        this.station_id = station_id;
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

    public Station(int station_id, String description) {
        this.station_id = station_id;
        this.description = description;
    }


}
