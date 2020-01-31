package es.upm.miw.betca_tpv_spring.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Arrays;

@Document
public class Order {

    @Id
    private String id;
    private String description;
    @DBRef
    private Provider provider;
    private LocalDateTime openingDate;
    private LocalDateTime closingDate;
    private OrderLine[] orderLines;

    public Order(String description, Provider provider, OrderLine[] orderLines) {
        this.openingDate = LocalDateTime.now();
        this.description = description;
        this.provider = provider;
        this.orderLines = orderLines;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OrderLine[] getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(OrderLine[] orderLines) {
        this.orderLines = orderLines;
    }

    public String getId() {
        return id;
    }

    public Provider getProvider() {
        return provider;
    }

    public LocalDateTime getOpeningDate() {
        return openingDate;
    }

    public LocalDateTime getClosingDate() {
        return closingDate;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && id.equals(((Order) obj).id);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", provider=" + provider +
                ", openingDate=" + openingDate +
                ", closingDate=" + closingDate +
                ", orderLines=" + Arrays.toString(orderLines) +
                '}';
    }

    public void close() {
        Arrays.stream(this.orderLines).forEach(orderLine -> {
            if (orderLine.getFinalAmount() == null) {
                orderLine.setFinalAmount(orderLine.getRequiredAmount());
            }
        });
        this.closingDate = LocalDateTime.now();
    }

}
