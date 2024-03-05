package com.joel.br.JJ.TECH.DTO;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class ItemOrderDTO {

    private Long id;


    private Integer quantity;


    private BigDecimal unityPrice;

    private BigDecimal totalPrice;

}
