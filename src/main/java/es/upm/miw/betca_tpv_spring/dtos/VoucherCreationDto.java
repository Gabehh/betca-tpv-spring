package es.upm.miw.betca_tpv_spring.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import es.upm.miw.betca_tpv_spring.dtos.validations.BigDecimalPositive;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VoucherCreationDto {

    @BigDecimalPositive
    BigDecimal value;

    public VoucherCreationDto(){
        // Empty for framework
    }

    public VoucherCreationDto(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
