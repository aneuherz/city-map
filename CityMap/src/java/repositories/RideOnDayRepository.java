package repositories;

import entities.Ride;
import entities.RideOnDay;

import javax.persistence.TypedQuery;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Created by Edi on 10/01/16.
 */
public class RideOnDayRepository extends persistence.Repository<RideOnDay>
        implements persistence.IRepository {

    public RideOnDayRepository() {
        super(RideOnDay.class);
    }

    public RideOnDay create(Ride ride, Date ridestarttime) {
        RideOnDay rideOnDay = new RideOnDay(ride, ridestarttime);

        entityManager.persist(rideOnDay);

        return rideOnDay;
    }

    public RideOnDay find(int rideID, Date ridestarttime){
        RideOnDay.RideOnDayId pk = new RideOnDay.RideOnDayId(rideID, ridestarttime);
        return entityManager.find(RideOnDay.class, pk);
    }

    public List<RideOnDay> findNextRide(int stationID, Date date) {
        TypedQuery<RideOnDay> query = entityManager.createNamedQuery(
                "RideOnDay.findNextRide", RideOnDay.class);
        query.setParameter("stationID", stationID);
        query.setParameter("ridestarttime", date);
        return query.getResultList();
    }


    public List<RideOnDay> findAllRidesOnSpecificDateByLine(int lineID, Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.AM_PM, Calendar.AM);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date lowerLimit = calendar.getTime();

        calendar.set(Calendar.AM_PM, Calendar.PM);
        calendar.set(Calendar.HOUR, 11);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);

        Date upperLimit = calendar.getTime();

        TypedQuery<RideOnDay> query = entityManager.createNamedQuery(
                "RideOnDay.findAllRidesOnSpecificDateByLine", RideOnDay.class);
        query.setParameter("lineID", lineID);
        query.setParameter("dayLowerLimit", lowerLimit);
        query.setParameter("dayUpperLimit", upperLimit);
        return query.getResultList();
    }

    public void removeWithAllReferences(int rideID, Date date){
        RideOnDay rideOnDay = entityManager.merge(find(rideID, date));
        entityManager.remove(rideOnDay);
    }

}
