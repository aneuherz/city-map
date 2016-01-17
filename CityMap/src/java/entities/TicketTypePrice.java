package entities;/*
 * project    company
*/


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@IdClass(TicketTypePrice.TicketTypeId.class)
@Table(schema = "TICKETS")
public class TicketTypePrice {

    @Id
    private int tickettype_id;

    @Id
    private Date validfrom;

    private long baseprice;

    @ManyToOne(fetch=FetchType.LAZY)
    @PrimaryKeyJoinColumn(name="TICKETTYPE_ID", referencedColumnName = "TICKETTYPE_ID")
    private TicketType ticketType;

    protected TicketTypePrice() {
    }

    public TicketTypePrice(int tickettype_id, Date validfrom, long baseprice) {
        this.tickettype_id = tickettype_id;
        this.validfrom = validfrom;
        this.baseprice = baseprice;
    }

    public String toString() {
        return "entities.TicketType_FK id: " + getTickettype_id() + " valid from: " + getValidfrom();
    }

    public int getTickettype_id() {
        return tickettype_id;
    }

    public void setTickettype_id(int id) {
        this.tickettype_id = id;
    }

    public Date getValidfrom() {
        return validfrom;
    }

    public void setValidfrom(Date validfrom) {
        this.validfrom = validfrom;
    }

    public long getBaseprice() {
        return baseprice;
    }

    public void setBaseprice(long baseprice) {
        this.baseprice = baseprice;
    }

    static class TicketTypeId implements Serializable {
        int tickettype_id;
        Date validfrom;

    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
        if (!ticketType.getTicketTypePrices().contains(this)) { // warning this may cause performance issues if you have a large data set since this operation is O(n)
            ticketType.getTicketTypePrices().add(this);

        }
    }


}

