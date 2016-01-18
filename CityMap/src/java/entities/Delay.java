package entities;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Edi on 25/11/15.
 */

@Entity
@Table(schema = "CITYMAP")
public class Delay {
    @Id
    @Column(name = "ride_id")
    private int rideID;

    @Id
    private Date ridestarttime;

    private int delayinminutes;

    private String reason;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumns({@PrimaryKeyJoinColumn(name = "RIDE_ID", referencedColumnName = "RIDE_ID"),
            @PrimaryKeyJoinColumn(name = "ridestarttime", referencedColumnName = "ridestarttime")})
    private RideOnDay rideOnDay;

    protected Delay() {
    }

    public Delay(int rideID, Date ridestarttime, int delayinminutes, String reason) {
        this.rideID = rideID;
        this.ridestarttime = ridestarttime;
        this.delayinminutes = delayinminutes;
        this.reason = reason;
    }

    public int getRideID() {
        return rideID;
    }

    public void setRideID(int rideID) {
        this.rideID = rideID;
    }

    public Date getRidestarttime() {
        return ridestarttime;
    }

    public void setRidestarttime(Date ridestarttime) {
        this.ridestarttime = ridestarttime;
    }

    public int getDelayinminutes() {
        return delayinminutes;
    }

    public void setDelayinminutes(int delayinminutes) {
        this.delayinminutes = delayinminutes;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public RideOnDay getRideOnDay() {
        return rideOnDay;
    }

    public void setRideOnDay(RideOnDay rideOnDay) {
        this.rideOnDay = rideOnDay;
    }
}
