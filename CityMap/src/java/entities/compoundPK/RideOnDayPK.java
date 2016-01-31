package entities.compoundPK;

import javax.persistence.Embeddable;

/**
 * Created by Edi on 31/01/16.
 */

@Embeddable
public class RideOnDayPK {


    private Integer ride_id;       // MUST be same name as table col.
    private Integer ridestarttime;        // MUST be same name as table col.

    protected RideOnDayPK() {
    }

    RideOnDayPK(int employeeId, int projectId) {
        this.ride_id = employeeId;
        this.ridestarttime = projectId;
    }


    @Override
    public boolean equals(Object o) {
        return o instanceof RideOnDayPK
                && ((RideOnDayPK) o).ride_id == ride_id
                && ((RideOnDayPK) o).ridestarttime == ridestarttime;
    }

    @Override
    public int hashCode() {
        return ride_id + ridestarttime;
    }

    @Override
    public String toString() {
        return ride_id + ";" + ridestarttime;
    }


}
