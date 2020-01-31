package es.upm.miw.betca_tpv_spring.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Stream;

@Document
public class Ticket {

    private static final String DATE_FORMAT = "yyyyMMdd";

    @Id
    private String id;
    private LocalDateTime creationDate;
    private String reference;
    private Shopping[] shoppingList;
    private BigDecimal cash;
    private BigDecimal card;
    private BigDecimal voucher;
    private String note;
    @DBRef
    private User user;

    public Ticket() {
        this.creationDate = LocalDateTime.now();
        this.reference = new Encode().generateUUIDUrlSafe();
        this.note = "";
        this.card = BigDecimal.ZERO;
        this.cash = BigDecimal.ZERO;
        this.voucher = BigDecimal.ZERO;
    }

    public Ticket(int idOfDay, BigDecimal card, BigDecimal cash, BigDecimal voucher, Shopping[] shoppingList,
                  User user, String note) {
        this();
        this.setId(idOfDay);
        this.shoppingList = shoppingList;
        this.user = user;
        this.addPay(card, cash, voucher);
        this.note = note;
    }

    public void addPay(BigDecimal card, BigDecimal cash, BigDecimal voucher) {
        this.card = this.card.add(card);
        this.cash = this.cash.add(cash);
        this.voucher = this.voucher.add(voucher);
    }

    public BigDecimal pay() {
        return this.cash.add(this.card).add(this.voucher);
    }

    public BigDecimal debt() {
        return this.getTotal().subtract(this.pay());
    }

    public boolean isDebt() {
        return this.pay().compareTo(this.getTotal()) < 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setId(int idOfDay) {
        this.id = new SimpleDateFormat(DATE_FORMAT).format(new Date()) + idOfDay;
    }

    public int simpleId() {
        return Integer.parseInt(String.valueOf(id).substring(DATE_FORMAT.length()));
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public BigDecimal getCard() {
        return card;
    }

    public void setCard(BigDecimal card) {
        this.card = card;
    }

    public BigDecimal getVoucher() {
        return voucher;
    }

    public void setVoucher(BigDecimal voucher) {
        this.voucher = voucher;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public BigDecimal getTotal() {
        return Stream.of(this.shoppingList)
                .map(Shopping::getShoppingTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalCommitted() {
        return Stream.of(this.shoppingList)
                .filter(shopping -> ShoppingState.COMMITTED.equals(shopping.getShoppingState()))
                .map(Shopping::getShoppingTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && id.equals(((Ticket) obj).id);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + id + '\'' +
                ", creationDate=" + creationDate +
                ", reference='" + reference + '\'' +
                ", shoppingList=" + Arrays.toString(shoppingList) +
                ", cash=" + cash +
                ", card=" + card +
                ", voucher=" + voucher +
                ", note='" + note + '\'' +
                ", user=" + user +
                '}';
    }
}
