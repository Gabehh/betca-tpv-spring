package es.upm.miw.betca_tpv_spring.documents;

public enum Role {
    ADMIN, MANAGER, OPERATOR, CUSTOMER, AUTHENTICATED;

    public String roleName() {
        return "ROLE_" + this.toString();
    }

}
