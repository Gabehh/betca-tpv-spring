package es.upm.miw.betca_tpv_spring.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "articlesFamily")
public abstract class ArticlesFamily {

    @Id
    private String id;
    private FamilyType familyType;
    private String reference;

    public ArticlesFamily(FamilyType familyType, String reference) {
        this.familyType = familyType;
        this.reference = reference;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public FamilyType getFamilyType() {
        return familyType;
    }

    public String getReference() {
        return this.reference;
    }

    public abstract String getDescription();

    public abstract List<ArticlesFamily> getArticlesFamilyList();

    public abstract void add(ArticlesFamily articlesFamily);

    public abstract void remove(ArticlesFamily articlesFamily);

    public abstract Article getArticle();

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && id.equals(((ArticlesFamily) obj).id);
    }

    @Override
    public String toString() {
        return "ArticlesFamily{" +
                "id='" + id + '\'' +
                ", familyType=" + familyType +
                '}';
    }

}
