package com.sajath.allocation.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    private Integer orderId;

    @Column(name = "allocAmount")
    private Integer allocAmount;


    @Column(name = "status")
    private String status;


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
