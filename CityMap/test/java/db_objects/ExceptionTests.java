package db_objects;

import entities.Ride;
import entities.RideType;
import org.junit.Assert;
import org.junit.Test;
import spize.persistence.Transaction;

import javax.persistence.PersistenceException;

/**
 * Created by Edi on 31/01/16.
 */

public class ExceptionTests extends DBTests {

    @Test
    public void createDoublePK() {

        Transaction.begin();
        try {
            int id = rideTypeRepository.create("Monday to Friday").getRidetypeID();

            RideType actual = rideTypeRepository.find(id);
            Assert.assertNotNull(actual);
            rideTypeRepository.create(id, "Monday to Friday");
            Transaction.commit();
        } catch (Exception ex) {
            boolean isRightException = ex.getMessage().contains("duplicate key value violates unique constraint");
            Assert.assertTrue(isRightException);
        }
    }

    @Test
    public void updatePK() {
        try {
            Transaction.begin();
            RideType rideType = rideTypeRepository.find(1);
            rideType.setRidetypeID(99);
            Transaction.commit();
        } catch (Exception ex) {
            boolean isRightException = ex.getMessage().contains("mapped to a primary key column in the database. Updates are not allowed.");
            Assert.assertTrue(isRightException);
        }
    }

    @Test
    public void noPrivilegeToScheme() {
        try {
            Transaction.begin();
            em.createNativeQuery("INSERT INTO tickets.tickettype VALUES (19, 'test')").getResultList();
            Transaction.commit();
        } catch (PersistenceException ex) {
            boolean isRightException = ex.getMessage().contains("permission denied for relation tickettype");
            Assert.assertTrue(isRightException);
            userTransaction.rollback();
        }
    }

    @Test
    public void deleteWithFKReferencingEntity() {
        try {
            Assert.assertNotNull(rideRepository.find(1));
            Transaction.begin();
            Ride ride = em.merge(rideRepository.find(1));
            em.remove(ride);
            Transaction.commit();
        } catch (Exception ex) {
            boolean isRightException = ex.getMessage().contains("update or delete on table")
                    && ex.getMessage().contains("violates foreign key constraint");
            Assert.assertTrue(isRightException);
        }
    }


    @Test
    public void alreadySetFK() {
        try {

            Transaction.begin();
            RideType rideType = rideTypeRepository.find(1);
            Ride ride = rideRepository.find(1);
            ride.set(rideType);
            Transaction.commit();
        } catch (IllegalStateException ex) {
            boolean isRightException = ex.getMessage().contains("Department already set");
            Assert.assertTrue(isRightException);
            userTransaction.rollback();
        }

    }

}
