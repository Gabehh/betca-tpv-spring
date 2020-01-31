package es.upm.miw.betca_tpv_spring.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "articlesFamily")
public abstract class ArticlesFamily {

    @Id
    private String id;
    private FamilyType familyType;

    public ArticlesFamily() {
        // for framework
    }

    public ArticlesFamily(FamilyType familyType) {
        this.familyType = familyType;
    }

    public String getId() {
        return id;
    }

    public FamilyType getFamilyType() {
        return familyType;
    }

    public void setFamilyType(FamilyType familyType) {
        this.familyType = familyType;
    }

    public abstract String getReference();

    public abstract String getDescription();

    public abstract Integer getStock();

    public abstract void add(ArticlesFamily articlesFamily);

    public abstract void remove(ArticlesFamily articlesFamily);

    public abstract List<ArticlesFamily> getArticlesFamilyList();

    public abstract List<String> getArticleIdList();

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
