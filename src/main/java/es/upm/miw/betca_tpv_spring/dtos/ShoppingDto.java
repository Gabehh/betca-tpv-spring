package es.upm.miw.betca_tpv_spring.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import es.upm.miw.betca_tpv_spring.documents.Shopping;
import es.upm.miw.betca_tpv_spring.documents.ShoppingState;

import java.math.BigDecimal;

@JsonInclude(Include.NON_NULL)
public class ShoppingDto {

    private String code;

    private String description;

    private BigDecimal retailPrice;

    private Integer amount;

    private BigDecimal discount;

    private BigDecimal total;

    private Boolean committed;

    public ShoppingDto() {
        // Empty for framework
    }

    public ShoppingDto(String code, String description, BigDecimal retailPrice, int amount, BigDecimal discount, BigDecimal total,
                       Boolean committed) {
        this.code = code;
        this.description = description;
        this.retailPrice = retailPrice;
        this.amount = amount;
        this.discount = discount;
        this.total = total;
        this.committed = committed;
    }

    public ShoppingDto(Shopping shopping) {
        this.code = shopping.getArticleId();
        this.description = shopping.getDescription();
        this.retailPrice = shopping.getRetailPrice();
        this.amount = shopping.getAmount();
        this.discount = shopping.getDiscount();
        this.total = shopping.getShoppingTotal();
        this.committed = shopping.getShoppingState().equals(ShoppingState.COMMITTED);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public boolean isCommitted() {
        return committed;
    }

    public void setCommitted(boolean committed) {
        this.committed = committed;
    }

    @Override
    public String toString() {
        return "ShoppingDto [code=" + code + ", description=" + description + ", retailPrice=" + retailPrice + ", amount=" + amount
                + ", discount=" + discount + ", total=" + total + ", committed=" + committed + "]";
    }

}
