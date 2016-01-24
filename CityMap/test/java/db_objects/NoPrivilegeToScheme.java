package db_objects;

import entities.TicketType;
import entities.TicketTypeEnum;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.*;

/**
 * Created by Alexander on 24.01.2016.
 */

@org.junit.FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class NoPrivilegeToScheme extends DBTests {
    @Test
    public void createSchemeTicketShouldFail() {
        boolean status = false;
        try {
            userTransaction.begin();
            TicketType ticketType = new TicketType(8, TicketTypeEnum.DOG_TICKET);
            em.persist(ticketType);
            userTransaction.commit();
        }
        catch (RollbackException ex){
            status = true;
        }
        Assert.assertEquals(true,status);
    }
}
