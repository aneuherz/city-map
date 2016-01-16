package entities;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "LADARPHI15")
public class TicketType {

    @Id
    private int tickettype_id;

    @Enumerated(EnumType.STRING)
    private TicketTypeEnum type;

    @OneToMany(mappedBy = "ticketType")
    private List<TicketTypePrice> ticketTypePrices;

    protected TicketType() {
    }

    public TicketType(int tickettype_id, TicketTypeEnum type) {
        this.tickettype_id = tickettype_id;
        this.type = type;
    }

    public void addTicketTypePrice(TicketTypePrice ticketTypePrice) {
        this.ticketTypePrices.add(ticketTypePrice);
        if (ticketTypePrice.getTicketType() != this) {
            ticketTypePrice.setTicketType(this);
        }
    }

    public int getTickettype_id() {
        return tickettype_id;
    }

    public void setTickettype_id(int ticketType) {
        this.tickettype_id = ticketType;
    }

    public TicketTypeEnum getType() {
        return type;
    }

    public void setType(TicketTypeEnum type) {
        this.type = type;
    }

    public void setType(String TicketTypes) {
        this.type = type;
    }

    public String toString() {
        return "entities.TicketType id: " + getTickettype_id() + " description: " + getType();
    }

    public List<TicketTypePrice> getTicketTypePrices() {
        return ticketTypePrices;
    }

    public void setTicketTypePrices(List<TicketTypePrice> ticketTypePrices) {
        this.ticketTypePrices = ticketTypePrices;
    }
}
