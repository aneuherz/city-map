package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * Created by Edi on 25/11/15.
 */
@NamedQueries({
        @NamedQuery(name = "Delay.findAllDelaysByLineID",
                query = "SELECT d FROM Delay d " +
                        "JOIN d.rideOnDay rod " +
                        "JOIN rod.ride r " +
                        "WHERE r.line.lineID=:lineID"),
        @NamedQuery(name = "Delay.findByIdAndDate",
                query = "SELECT d FROM Delay d " +
                        "WHERE d.rideOnDay.ride.rideID=:rideID " +
                        "AND d.rideOnDay.ridestarttime=:ridestarttime")
})
@Entity
@Table(schema = "CITYMAP")
@IdClass(Delay.DelayId.class)
public class Delay {

    @Id
    @Column(name = "ride_id")
    private int ride;

    @Id
    private Date ridestarttime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "RIDE_ID", referencedColumnName = "RIDE_ID"),
            @JoinColumn(name = "ridestarttime", referencedColumnName = "ridestarttime")
    })
    private RideOnDay rideOnDay;

    private int delayinminutes;

    private String reason;

    protected Delay() {
    }

    public Delay(RideOnDay rideOnDay, int delayinminutes, String reason) {
        this.rideOnDay = rideOnDay;
        this.delayinminutes = delayinminutes;
        this.reason = reason;
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


    @Override
    public boolean equals(Object o) {
        return o instanceof Delay && ((Delay) o).getRideOnDay().getRide().getRideID() == rideOnDay.getRide().getRideID()
                && ((Delay) o).getRideOnDay().getRidestarttime() == rideOnDay.getRidestarttime();
    }

    public static class DelayId implements Serializable {

        @Column(name = "ride_id")
        private int ride;
        private Date ridestarttime;

        public DelayId(int ride, Date ridestarttime) {
            this.ride = ride;
            this.ridestarttime = ridestarttime;
        }
    }
}
