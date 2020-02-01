package es.upm.miw.betca_tpv_spring.documents;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "articlesFamily")
public class FamilyComposite extends ArticlesFamily {

    private String description;
    @DBRef(lazy = true)
    private List<ArticlesFamily> articlesFamilyList;

    public FamilyComposite(FamilyType familyType, String reference, String description) {
        super(familyType, reference);
        this.description = description;
        this.articlesFamilyList = new ArrayList<>();
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void add(ArticlesFamily articlesFamilyList) {
        this.articlesFamilyList.add(articlesFamilyList);
    }

    @Override
    public void remove(ArticlesFamily articlesFamilyList) {
        this.articlesFamilyList.remove(articlesFamilyList);
    }

    @Override
    public List<ArticlesFamily> getArticlesFamilyList() {
        return this.articlesFamilyList;
    }

    @Override
    public Article getArticle() {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && this.getId().equals(((FamilyComposite) obj).getId());
    }

    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }

    @Override
    public String toString() {
        List<String> list = new ArrayList<>();
        for (ArticlesFamily item : articlesFamilyList) {
            list.add("DBRef:" + item.getId());
        }
        return "FamilyComposite [" + super.toString() + ", description=" + description
                + ", articlesFamilyList=" + list + "]";
    }


}
