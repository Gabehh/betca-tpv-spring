package es.upm.miw.betca_tpv_spring.dtos;

import es.upm.miw.betca_tpv_spring.documents.ArticlesFamily;

import java.util.List;

public class FamilyCompositeDto {

    private String description;

    private List<ArticlesFamily> articlesFamilyList;

    public FamilyCompositeDto() {
    }

    public FamilyCompositeDto(String description, List<ArticlesFamily> articlesFamilyList) {
        this.description = description;
        this.articlesFamilyList = articlesFamilyList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ArticlesFamily> getArticlesFamilyList() {
        return articlesFamilyList;
    }

    public void setArticlesFamilyList(List<ArticlesFamily> articlesFamilyList) {
        this.articlesFamilyList = articlesFamilyList;
    }
}
