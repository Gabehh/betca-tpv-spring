package es.upm.miw.betca_tpv_spring.documents;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Document(collection = "articlesFamily")
public class FamilyArticle extends ArticlesFamily {

    @DBRef
    private Article article;

    public FamilyArticle() {
    }

    public FamilyArticle(Article article) {
        super(FamilyType.ARTICLE);
        this.article = article;
    }

    @Override
    public String getReference() {
        return this.article.getReference();
    }

    @Override
    public String getDescription() {
        return this.article.getDescription();
    }

    @Override
    public Integer getStock() {
        return this.article.getStock();
    }

    @Override
    public void add(ArticlesFamily familyComponent) {
        // Do nothing
    }

    @Override
    public void remove(ArticlesFamily familyComponent) {
        // Do nothing
    }

    @Override
    public List<ArticlesFamily> getArticlesFamilyList() {
        return Collections.emptyList();
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && this.getId().equals(((FamilyArticle) obj).getId());
    }

    @Override
    public String toString() {
        return "FamilyArticle [" + super.toString() + "article=" + article + "]";
    }

    @Override
    public List<String> getArticleIdList() {
        return Arrays.asList(this.article.getCode());
    }

}
