package es.upm.miw.betca_tpv_spring.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import es.upm.miw.betca_tpv_spring.documents.Provider;

public class ProviderDto {

    private String id;

    private String company;

    private String nif;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String address;

    private String phone;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String note;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean active;

    public ProviderDto() {
        // Empty for framework
    }

    public ProviderDto(String id, String company, String nif, String address, String phone, String email, String note, Boolean active) {
        this.id = id;
        this.company = company;
        this.nif = nif;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.note = note;
        this.active = active;
    }

    public ProviderDto(Provider provider) {
        this(
                provider.getId(),
                provider.getCompany(),
                provider.getNif(),
                provider.getAddress(),
                provider.getPhone(),
                provider.getEmail(),
                provider.getNote(),
                provider.isActive()
        );
    }

    public String getId() {
        return this.id;
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
        return "ProviderDto{" +
                "id='" + this.id + '\'' +
                ", company='" + this.company + '\'' +
                ", nif='" + this.nif + '\'' +
                ", address='" + this.address + '\'' +
                ", phone='" + this.phone + '\'' +
                ", email='" + this.email + '\'' +
                ", note='" + this.note + '\'' +
                ", active=" + this.active +
                '}';
    }
}
