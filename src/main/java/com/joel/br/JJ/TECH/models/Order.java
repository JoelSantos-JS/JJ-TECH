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
@Table
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code = UUID.randomUUID().toString();

    private BigDecimal subTotal;

    private BigDecimal feeShipping;
    private BigDecimal valueTotal;

    @CreationTimestamp
    private Instant createDate;
    private Instant confirmationDate;
    private Instant cancellationDate;
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


}
