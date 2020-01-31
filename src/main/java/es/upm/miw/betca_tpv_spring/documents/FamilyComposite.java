package es.upm.miw.betca_tpv_spring.documents;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document(collection = "articlesFamily")
public class FamilyComposite extends ArticlesFamily {

    private String reference;
    private String description;
    @DBRef(lazy = true)
    private List<ArticlesFamily> articlesFamilyList;

    public FamilyComposite() {
        super(FamilyType.ARTICLES);
        this.articlesFamilyList = new ArrayList<>();
    }

    public FamilyComposite(FamilyType familyType, String reference, String description) {
        super(familyType);
        this.reference = reference;
        this.description = description;
        this.articlesFamilyList = new ArrayList<>();
    }

    @Override
    public String getReference() {
        return this.reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Integer getStock() {
        return null;
    }

    @Override
    public void add(ArticlesFamily articlesFamilyList) {
        this.articlesFamilyList.add(articlesFamilyList);
    }

    @Override
    public void remove(ArticlesFamily articlesFamilyList) {
        this.articlesFamilyList.remove(articlesFamilyList);
    }

    public List<ArticlesFamily> getFamilyCompositeList() {
        return articlesFamilyList;
    }

    public void setFamilyCompositeList(List<ArticlesFamily> familyCompositeList) {
        this.articlesFamilyList = familyCompositeList;
    }

    @Override
    public List<ArticlesFamily> getArticlesFamilyList() {
        return this.articlesFamilyList;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && this.getId().equals(((FamilyComposite) obj).getId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), reference, description, articlesFamilyList);
    }

    @Override
    public String toString() {
        List<String> list = new ArrayList<>();
        for (ArticlesFamily item : articlesFamilyList) {
            list.add("DBRef:" + item.getId());
        }
        return "FamilyComposite [" + super.toString() + " reference=" + reference + ", description=" + description
                + ", articlesFamilyList=" + list + "]";
    }

    @Override
    public List<String> getArticleIdList() {
        List<String> articleIdList = new ArrayList<>();
        for (ArticlesFamily articlesFamily : this.articlesFamilyList) {
            articleIdList.addAll(articlesFamily.getArticleIdList());
        }
        return articleIdList;
    }

}
