package es.upm.miw.betca_tpv_spring.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Provider {

    @Id
    private String id;
    @Indexed(unique = true)
    private String company;
    private String nif;
    private String address;
    private String phone;
    private String email;
    private String note;
    private Boolean active;

    public Provider() {
        // for framework
    }

    public static Builder builder(String company) {
        return new Builder(company);
    }

    public String getId() {
        return id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean isActive() {
        return active;
    }

    @Override
    public int hashCode() {
        return this.company.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (company.equals(((Provider) obj).company));
    }

    @Override
    public String toString() {
        return "Provider{" +
                "id='" + id + '\'' +
                ", company='" + company + '\'' +
                ", nif='" + nif + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", note='" + note + '\'' +
                ", active=" + active +
                '}';
    }

    public static class Builder {
        private Provider provider;

        private Builder(String company) {
            this.provider = new Provider();
            this.provider.company = company;
            this.provider.active = true;
        }

        public Builder company(String company) {
            this.provider.company = company;
            return this;
        }

        public Builder nif(String nif) {
            this.provider.nif = nif;
            return this;
        }

        public Builder address(String address) {
            this.provider.address = address;
            return this;
        }

        public Builder phone(String phone) {
            this.provider.phone = phone;
            return this;
        }

        public Builder email(String email) {
            this.provider.email = email;
            return this;
        }

        public Builder note(String note) {
            this.provider.note = note;
            return this;
        }

        public Builder active(Boolean active) {
            this.provider.active = active;
            return this;
        }

        public Provider build() {
            return this.provider;
        }
    }

}
