package es.upm.miw.betca_tpv_spring.documents;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Shopping {

    private String articleId;
    private String description;
    private BigDecimal retailPrice;
    private Integer amount;
    private BigDecimal discount;
    private ShoppingState shoppingState;

    public Shopping() {
        //empty to the framework
    }

    public Shopping(Integer amount, BigDecimal discount, ShoppingState shoppingState,
                    String articleId, String description, BigDecimal retailPrice) {
        this.amount = amount;
        this.setDiscount(discount);
        this.articleId = articleId;
        this.description = description;
        this.retailPrice = retailPrice;
        this.shoppingState = shoppingState;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        if (discount == null) {
            discount = BigDecimal.ZERO;
        }
        this.discount = discount.setScale(2, RoundingMode.HALF_UP);
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
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

    public ShoppingState getShoppingState() {
        return shoppingState;
    }

    public void setShoppingState(ShoppingState shoppingState) {
        this.shoppingState = shoppingState;
    }

    public BigDecimal getShoppingTotal() {
        return retailPrice.multiply(new BigDecimal(amount))
                .multiply(new BigDecimal("1").subtract(this.discount.divide(new BigDecimal("100"))));
    }

    @Override
    public String toString() {
        return "Shopping{" +
                "articleId='" + articleId + '\'' +
                ", description='" + description + '\'' +
                ", retailPrice=" + retailPrice +
                ", amount=" + amount +
                ", discount=" + discount +
                ", shoppingState=" + shoppingState +
                '}';
    }
}
