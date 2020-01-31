package es.upm.miw.betca_tpv_spring.documents;

import org.springframework.data.mongodb.core.mapping.DBRef;

public class OrderLine {

    @DBRef
    private Article article;
    private Integer requiredAmount;
    private Integer finalAmount;

    public OrderLine() {
        // for framework
    }

    public OrderLine(Article article, int requiredAmount) {
        this.article = article;
        this.requiredAmount = requiredAmount;
        this.finalAmount = requiredAmount;
    }

    public Integer getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(Integer finalAmount) {
        this.finalAmount = finalAmount;
    }

    public Article getArticle() {
        return article;
    }

    public Integer getRequiredAmount() {
        return requiredAmount;
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "article=" + article +
                ", requiredAmount=" + requiredAmount +
                ", finalAmount=" + finalAmount +
                '}';
    }

}
