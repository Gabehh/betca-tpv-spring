package es.upm.miw.betca_tpv_spring.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

@Document
public class
Budget {

    @Id
    private String id;
    private LocalDateTime creationDate;
    private Shopping[] shoppingList;

    public Budget() {
        this(null);
    }

    public Budget(Shopping[] shoppingList) {
        this.creationDate = LocalDateTime.now();
        this.shoppingList = shoppingList;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Shopping[] getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(Shopping[] shoppingList) {
        this.shoppingList = shoppingList;
    }

    public BigDecimal getBudgetTotal() {
        BigDecimal total = new BigDecimal(0);
        for (Shopping shopping : shoppingList) {
            total = total.add(shopping.getShoppingTotal());
        }
        return total;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((Budget) obj).id));
    }

    @Override
    public String toString() {
        return "Budget{" +
                "id='" + id + '\'' +
                ", creationDate=" + creationDate +
                ", shoppingList=" + Arrays.toString(shoppingList) +
                '}';
    }
}
