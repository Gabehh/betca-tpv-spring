package es.upm.miw.betca_tpv_spring.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ProviderCreationDto {

    private String company;

    private String nif;

    @JsonInclude(Include.NON_NULL)
    private String address;

    private String phone;

    @JsonInclude(Include.NON_NULL)
    private String email;

    @JsonInclude(Include.NON_NULL)
    private String note;

    @JsonInclude(Include.NON_NULL)
    private Boolean active;

    public ProviderCreationDto() {
        // Empty for framework
    }

    public ProviderCreationDto(String company, String nif, String address, String phone, String email, String note, Boolean active) {
        this.company = company;
        this.nif = nif;
        this.address = address;
        this.phone = phone;
        this.email = company;
        this.note = nif;
        this.active = active;
    }

    public String getCompany() {
        return this.company;
    }

    public String getNif() {
        return this.nif;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getAddress() {
        return this.address;
    }

    public String getEmail() {
        return this.email;
    }

    public String getNote() {
        return this.note;
    }

    public Boolean getActive() {
        return this.active;
    }

    @Override
    public String toString() {
        return "ProviderCreationDto{" +
                "company='" + this.company + '\'' +
                ", nif='" + this.nif + '\'' +
                ", address='" + this.address + '\'' +
                ", phone='" + this.phone + '\'' +
                ", email='" + this.email + '\'' +
                ", note='" + this.note + '\'' +
                ", active=" + this.active +
                '}';
    }
}
