package com.joel.br.JJ.TECH.DTO;

import com.joel.br.JJ.TECH.models.FormPayment;
import lombok.Data;

@Data
public class FormPaymentDTO {

    private Long id;
    private String description;

    public FormPaymentDTO() {
    }

    public FormPaymentDTO(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public FormPaymentDTO(FormPayment payment) {
        id = payment.getId();
        description = payment.getDescription();
    }
}
