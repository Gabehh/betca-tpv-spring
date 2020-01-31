package es.upm.miw.betca_tpv_spring.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Document
public class Invoice {

    private static final String DATE_FORMAT = "yyyy";

    @Id
    private String id;
    private LocalDateTime creationDate;
    private BigDecimal baseTax;
    private BigDecimal tax;
    @DBRef
    private Ticket ticket;
    @DBRef
    private User user;

    public Invoice() {
        creationDate = LocalDateTime.now();
    }

    public Invoice(int idOfYear, BigDecimal baseTax, BigDecimal tax, User user, Ticket ticket) {
        this();
        this.id = new SimpleDateFormat(DATE_FORMAT).format(new Date()) + idOfYear;
        this.baseTax = baseTax;
        this.tax = tax;
        this.user = user;
        this.ticket = ticket;
    }

    public String getId() {
        return id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public LocalDateTime getCreationDated() {
        return creationDate;
    }

    public int simpleId() {
        return Integer.parseInt(String.valueOf(id).substring(DATE_FORMAT.length()));
    }

    public BigDecimal getBaseTax() {
        return baseTax;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((Invoice) obj).id));
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id='" + id + '\'' +
                ", creationDate=" + creationDate +
                ", baseTax=" + baseTax +
                ", tax=" + tax +
                ", ticket=" + ticket +
                ", user=" + user +
                '}';
    }
}
