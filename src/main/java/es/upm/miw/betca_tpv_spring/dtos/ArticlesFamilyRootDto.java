package es.upm.miw.betca_tpv_spring.dtos;

import es.upm.miw.betca_tpv_spring.documents.FamilyType;

public class ArticlesFamilyRootDto {

    private String code;

    private FamilyType familyType;

    private String reference;

    public ArticlesFamilyRootDto() {
    }

    public ArticlesFamilyRootDto(String code, FamilyType familyType, String reference) {
        this.code = code;
        this.familyType = familyType;
        this.reference = reference;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
        return "ArticlesFamilyRootDto{" +
                "code='" + code + '\'' +
                ", familyType=" + familyType +
                ", reference='" + reference + '\'' +
                '}';
    }
}
