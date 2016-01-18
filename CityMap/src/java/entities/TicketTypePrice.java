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
    @Column(name = "TicketType_ID")
    private int ticketTypeID;

    @Id
    private Date validfrom;

    private long baseprice;

    @ManyToOne(fetch=FetchType.LAZY)
    @PrimaryKeyJoinColumn(name="TICKETTYPE_ID", referencedColumnName = "TICKETTYPE_ID")
    private TicketType ticketType;

    protected TicketTypePrice() {
    }

    public TicketTypePrice(int ticketTypeID, Date validfrom, long baseprice) {
        this.ticketTypeID = ticketTypeID;
        this.validfrom = validfrom;
        this.baseprice = baseprice;
    }

    public String toString() {
        return "entities.TicketType_FK id: " + getTicketTypeID() + " valid from: " + getValidfrom();
    }

    public int getTicketTypeID() {
        return ticketTypeID;
    }

    public void setTicketTypeID(int id) {
        this.ticketTypeID = id;
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
        int ticketTypeID;
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

