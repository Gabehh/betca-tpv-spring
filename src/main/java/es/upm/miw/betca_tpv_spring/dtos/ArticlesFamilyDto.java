package es.upm.miw.betca_tpv_spring.dtos;

import es.upm.miw.betca_tpv_spring.documents.FamilyType;
import es.upm.miw.betca_tpv_spring.documents.ArticlesFamily;

public class ArticlesFamilyDto {

    private String id;

    private FamilyType familyType;

    private String reference;

    private String description;

    public ArticlesFamilyDto() {
    }

    public ArticlesFamilyDto(String id, FamilyType familyType, String reference) {
        this.id = id;
        this.familyType = familyType;
        this.reference = reference;
    }

    public ArticlesFamilyDto(ArticlesFamily articlesFamily) {
        this.familyType = articlesFamily.getFamilyType();
        this.reference = articlesFamily.getReference();
        this.description = articlesFamily.getDescription();
    }

    public String getCode() {
        return id;
    }

    public void setCode(String code) {
        this.id = code;
    }

    public FamilyType getFamilyType() {
        return familyType;
    }

    public void setFamilyType(FamilyType familyType) {
        this.familyType = familyType;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public String toString() {
        return "ArticlesFamilyDto{" +
                "id='" + id + '\'' +
                ", familyType=" + familyType +
                ", reference='" + reference + '\'' +
                '}';
    }
}
