package es.upm.miw.betca_tpv_spring.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document
public class CustomerDiscount {

    @Id
    private String id;
    private String description;
    private LocalDateTime registrationDate;
    private BigDecimal discount;
    private BigDecimal minimumPurchase;
    @DBRef(lazy = true)
    private User user;

    public CustomerDiscount() {
        this.registrationDate = LocalDateTime.now();
    }

    public CustomerDiscount(String description, BigDecimal discount, BigDecimal minimumPurchase, User user) {
        this();
        this.description = description;
        this.discount = discount;
        this.minimumPurchase = minimumPurchase;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getMinimumPurchase() {
        return minimumPurchase;
    }

    public void setMinimumPurchase(BigDecimal minimumPurchase) {
        this.minimumPurchase = minimumPurchase;
    }

    public User getUser() {
        return user;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((CustomerDiscount) obj).id));
    }

    @Override
    public String toString() {
        return "CustomerDiscount{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", registrationDate=" + registrationDate +
                ", discount=" + discount +
                ", minimumPurchase=" + minimumPurchase +
                ", user=" + user +
                '}';
    }
}
