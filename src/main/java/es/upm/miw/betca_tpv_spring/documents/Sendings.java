package es.upm.miw.betca_tpv_spring.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class Sendings {

    @Id
    private String id;
    private String reference;
    private String username;
    private LocalDateTime creationDate;
    private Boolean estado;

    public Sendings(){
        this.reference = new Encode().generateUUIDUrlSafe();
        this.creationDate = LocalDateTime.now();
        this.estado = false;
    }

    public Sendings(String id, String username) {
        this();
        this.id = id;
        this.username = username;
    }

    public Boolean enEstado(){
        return estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && id.equals(((Sendings) obj).id);
    }


    @Override
    public String toString() {
        return "Sendings{" +
                "id='" + id + '\'' +
                ", reference='" + reference + '\'' +
                ", creationDate=" + creationDate +
                ", estado=" + estado +
                ", username='" + username + '\'' +
                '}';
    }
}
