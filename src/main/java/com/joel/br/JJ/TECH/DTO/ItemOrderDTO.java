package com.joel.br.JJ.TECH.DTO;

import com.joel.br.JJ.TECH.models.ItemOrder;
import com.joel.br.JJ.TECH.models.Product;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class ItemOrderDTO {

    private Long id;


    private Integer quantity;


    private BigDecimal unityPrice;

    private BigDecimal totalPrice;

        private OrderDTO order;
    private ProductDTO product;

    public ItemOrderDTO() {
    }

    public ItemOrderDTO(Long id, Integer quantity, BigDecimal unityPrice, BigDecimal totalPrice) {
        this.id = id;
        this.quantity = quantity;
        this.unityPrice = unityPrice;
        this.totalPrice = totalPrice;
    }

    public ItemOrderDTO(ItemOrder itemOrder) {
        this.id = itemOrder.getId();
        this.quantity = itemOrder.getQuantity();
        this.unityPrice = itemOrder.getUnityPrice();
        this.totalPrice = itemOrder.getTotalPrice();
        this.order = new OrderDTO(itemOrder.getOrder());


        if(itemOrder.getProduct() != null) {
            this.product = new ProductDTO(itemOrder.getProduct());
        }
    }
}
