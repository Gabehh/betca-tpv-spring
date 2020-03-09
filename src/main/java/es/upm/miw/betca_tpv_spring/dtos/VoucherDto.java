package es.upm.miw.betca_tpv_spring.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import es.upm.miw.betca_tpv_spring.dtos.validations.BigDecimalPositive;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class VoucherDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;

    @BigDecimalPositive
    private BigDecimal value;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime creationDate;

    private LocalDateTime dateOfUse;

    public VoucherDto() {
        //empty for framework
    }

    public VoucherDto(String id, BigDecimal value, LocalDateTime creationDate, LocalDateTime dateOfUse) {
        this.id = id;
        this.value = value;
        this.creationDate = creationDate;
        this.dateOfUse = dateOfUse;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getDateOfUse() {
        return dateOfUse;
    }

    public void setDateOfUse(LocalDateTime dateOfUse) {
        this.dateOfUse = dateOfUse;
    }

    @Override
    public String toString() {
        return "VoucherDto{" +
                "id='" + id + '\'' +
                ", value=" + value +
                ", creationDate=" + creationDate +
                ", dateOfUse=" + dateOfUse +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoucherDto that = (VoucherDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(value, that.value) &&
                Objects.equals(creationDate, that.creationDate) &&
                Objects.equals(dateOfUse, that.dateOfUse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, creationDate, dateOfUse);
    }
}
