package com.joel.br.JJ.TECH.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.BeanInfo;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table
@Entity
public class ItemOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Integer quantity;

    private BigDecimal unityPrice;

    private BigDecimal totalPrice;
    @ManyToOne
    private Order order;




    @ManyToOne
    private Product product;



    public void  calculateTotalPrice() {
        BigDecimal unityPrice = this.getUnityPrice();
        Integer quantity = this.getQuantity();

        if(unityPrice == null) {
            unityPrice = BigDecimal.ZERO;
        }
        if(quantity == null) {
            quantity = 0;
        }

        this.totalPrice = unityPrice.multiply(BigDecimal.valueOf(quantity));
    }



}
