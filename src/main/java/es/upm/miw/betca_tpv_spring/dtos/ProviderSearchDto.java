package es.upm.miw.betca_tpv_spring.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ProviderSearchDto {

    @JsonInclude(Include.NON_NULL)
    private String company;

    @JsonInclude(Include.NON_NULL)
    private String nif;

    @JsonInclude(Include.NON_NULL)
    private String phone;

    public ProviderSearchDto() {
        // Empty for framework
    }

    public ProviderSearchDto(String company, String nif, String phone) {
        this.company = company;
        this.nif = nif;
        this.phone = phone;
    }

    public String getCompany() {
        return company;
    }

    public String getNif() {
        return nif;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "ProviderSearchDto{" +
                "company=" + this.company +
                ", nif=" + this.nif +
                ", phone='" + this.phone + '\'' +
                '}';
    }
}
