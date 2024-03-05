package com.joel.br.JJ.TECH.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code = UUID.randomUUID().toString();
    @Column(name = "sub_total")
    private BigDecimal subTotal;
    @Column(name = "fee_shipping")
    private BigDecimal feeShipping;

    @Column(name = "value_total")
    private BigDecimal valueTotal;

    @CreationTimestamp
    @Column(name = "created_date", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant createDate;
    @Column(name = "confirmation_date", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant confirmationDate;
    @Column(name = "cancellation_date", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant cancellationDate;
    @Column(name = "delivery_date", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    @CreationTimestamp
    private Instant deliveryDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    private User user;

    @ManyToOne
    private FormPayment payment;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<ItemOrder> items = new ArrayList<>();


    public void calculateFinalPrice() {
        getItems().forEach(ItemOrder::calculateTotalPrice);

        this.subTotal = getItems().stream()
                .map(item -> item.getTotalPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.valueTotal = this.subTotal.add(this.feeShipping);
    }


    public void addItem(ItemOrder itemOrder) {
        items.add(itemOrder);
        itemOrder.setOrder(this);
    }


}
