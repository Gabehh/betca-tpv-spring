package es.upm.miw.betca_tpv_spring.dtos;

import java.time.LocalDateTime;
import java.util.Objects;

public class VoucherSearchDto {
    private String id;
    private LocalDateTime firstDate;
    private LocalDateTime finalDate;

    public VoucherSearchDto(String id, LocalDateTime firstDate, LocalDateTime finalDate) {
        this.firstDate = firstDate;
        this.finalDate = finalDate;
        this.id = id;
    }

    public VoucherSearchDto(LocalDateTime firstDate, LocalDateTime finalDate) {
        this.id = null;
        this.firstDate = firstDate;
        this.finalDate = finalDate;
    }

    public VoucherSearchDto() {
    }

    public LocalDateTime getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(LocalDateTime firstDate) {
        this.firstDate = firstDate;
    }

    public LocalDateTime getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(LocalDateTime finalDate) {
        this.finalDate = finalDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "VoucherSearchDto{" +
                "firstDate=" + firstDate +
                ", finalDate=" + finalDate +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoucherSearchDto that = (VoucherSearchDto) o;
        return Objects.equals(firstDate, that.firstDate) &&
                Objects.equals(finalDate, that.finalDate) &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstDate, finalDate, id);
    }
}
