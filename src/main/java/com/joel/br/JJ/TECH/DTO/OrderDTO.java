package com.joel.br.JJ.TECH.DTO;

import com.joel.br.JJ.TECH.models.Order;
import com.joel.br.JJ.TECH.models.OrderStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class OrderDTO {

    private Long id;

    private String code = UUID.randomUUID().toString();

    private BigDecimal subTotal;

    private BigDecimal feeShipping;
    private BigDecimal valueTotal;



    private OrderStatus status;

    private UserDTO user;
    private FormPaymentDTO payment;


    private List<ItemOrderDTO> items;

    public OrderDTO() {
    }

    public OrderDTO(Long id, String code, BigDecimal subTotal, BigDecimal feeShipping, BigDecimal valueTotal, OrderStatus status, UserDTO user, FormPaymentDTO payment) {
        this.id = id;
        this.code = code;
        this.subTotal = subTotal;
        this.feeShipping = feeShipping;
        this.valueTotal = valueTotal;
        this.status = status;
        this.user = user;
        this.payment = payment;
    }

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.code = order.getCode();
        this.subTotal = order.getSubTotal();
        this.feeShipping = order.getFeeShipping();
        this.valueTotal = order.getValueTotal();
        this.status = order.getStatus();
        this.user = new UserDTO(order.getUser());
        this.payment = new FormPaymentDTO(order.getPayment());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getFeeShipping() {
        return feeShipping;
    }

    public void setFeeShipping(BigDecimal feeShipping) {
        this.feeShipping = feeShipping;
    }

    public BigDecimal getValueTotal() {
        return valueTotal;
    }

    public void setValueTotal(BigDecimal valueTotal) {
        this.valueTotal = valueTotal;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public FormPaymentDTO getPayment() {
        return payment;
    }

    public void setPayment(FormPaymentDTO payment) {
        this.payment = payment;
    }

    public List<ItemOrderDTO> getItems() {
        return items;
    }


}
