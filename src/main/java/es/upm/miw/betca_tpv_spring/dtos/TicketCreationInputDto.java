package es.upm.miw.betca_tpv_spring.dtos;

import es.upm.miw.betca_tpv_spring.dtos.validations.BigDecimalPositive;
import es.upm.miw.betca_tpv_spring.dtos.validations.ListNotEmpty;

import java.math.BigDecimal;
import java.util.List;

public class TicketCreationInputDto {

    private String userMobile;

    @BigDecimalPositive
    private BigDecimal cash;

    @BigDecimalPositive
    private BigDecimal card;

    @BigDecimalPositive
    private BigDecimal voucher;

    @ListNotEmpty
    private List<ShoppingDto> shoppingCart;

    private String note;

    public TicketCreationInputDto() {
        // Empty for framework
    }

    public TicketCreationInputDto(String userMobile, BigDecimal card, BigDecimal cash, BigDecimal voucher, List<ShoppingDto> shoppingCart,
                                  String note) {
        this.userMobile = userMobile;
        this.card = card;
        this.cash = cash;
        this.voucher = voucher;
        this.shoppingCart = shoppingCart;
        this.note = note;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
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

    public List<ShoppingDto> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(List<ShoppingDto> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "TicketCreationInputDto [userMobile=" + userMobile + ", cash=" + cash + ", card=" + card + ", voucher=" + voucher
                + ", shoppingCart=" + shoppingCart + ", note=" + note + "]";
    }

}
