package entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(schema = "TICKETS")
public class TicketType {

    @Id
    @Column(name = "TicketType_ID")
    private int ticketTypeID;

    @Enumerated(EnumType.STRING)
    private TicketTypeEnum type;

    @OneToMany(mappedBy = "ticketType")
    private List<TicketTypePrice> ticketTypePrices;

    protected TicketType() {
    }

    public TicketType(int ticketTypeID, TicketTypeEnum type) {
        this.ticketTypeID = ticketTypeID;
        this.type = type;
    }

    public void addTicketTypePrice(TicketTypePrice ticketTypePrice) {
        this.ticketTypePrices.add(ticketTypePrice);
        if (ticketTypePrice.getTicketType() != this) {
            ticketTypePrice.setTicketType(this);
        }
    }

    public int getTicketTypeID() {
        return ticketTypeID;
    }

    public void setTicketTypeID(int ticketType) {
        this.ticketTypeID = ticketType;
    }

    public TicketTypeEnum getType() {
        return type;
    }

    public void setType(TicketTypeEnum type) {
        this.type = type;
    }


    public String toString() {
        return "entities.TicketType_id: " + getTicketTypeID() + " description: " + getType();
    }

    public List<TicketTypePrice> getTicketTypePrices() {
        return ticketTypePrices;
    }

    public void setTicketTypePrices(List<TicketTypePrice> ticketTypePrices) {
        this.ticketTypePrices = ticketTypePrices;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof TicketType && ((TicketType) o).ticketTypeID == ticketTypeID;
    }
}
