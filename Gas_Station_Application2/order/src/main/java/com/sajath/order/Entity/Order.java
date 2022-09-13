package com.sajath.order.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Order")
public class Order {
    @Id
    @SequenceGenerator(
            name = "order_id_sequence",
            sequenceName = "order_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_id_sequence"
    )
    private Integer orderId;

    @Column(name = "allocAmount")
    private Integer allocAmount;

    @Column(name = "status")
    private String status;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;


    public Integer getOrderId() {
        return orderId;
    }
    public Integer getAllocAmount() {
        return allocAmount;
    }
    public String getStatus() {
        return status;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public void setAllocAmount(Integer allocAmount) {
        this.allocAmount = allocAmount;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", allocAmount=" + allocAmount +
                ", status='" + status + '\'' +
                '}';
    }
}
