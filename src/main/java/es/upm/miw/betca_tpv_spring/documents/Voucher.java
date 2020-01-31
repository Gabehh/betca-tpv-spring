package es.upm.miw.betca_tpv_spring.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document
public class Voucher {

    @Id
    private String id;
    private BigDecimal value;
    private LocalDateTime creationDate;
    private LocalDateTime dateOfUse;

    public Voucher() {
        this.creationDate = LocalDateTime.now();
        this.id = new Encode().generateUUIDUrlSafe();
    }

    public Voucher(BigDecimal value) {
        this();
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getDateOfUse() {
        return dateOfUse;
    }

    public void use() {
        if (this.dateOfUse != null) {
            throw new IllegalStateException("Voucher is already consumed");
        }
        dateOfUse = LocalDateTime.now();
    }

    public boolean isUsed() {
        return dateOfUse != null;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && id.equals(((Voucher) obj).id);
    }

    @Override
    public String toString() {
        return "Voucher{" +
                "id='" + id + '\'' +
                ", value=" + value +
                ", creationDate=" + creationDate +
                ", dateOfUse=" + dateOfUse +
                '}';
    }
}
