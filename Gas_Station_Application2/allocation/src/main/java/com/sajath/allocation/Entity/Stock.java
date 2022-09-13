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
@Entity(name = "Stock")
public class Stock {
    @Id
    @SequenceGenerator(
            name = "stock_id_sequence",
            sequenceName = "stock_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "stock_id_sequence"
    )
    private Integer id;

    @Column(name = "availableStock")
    private Integer availableStock;

    @Column(name = "allocatedAmount")
    private Integer allocatedAmount;

    public Integer getId() {
        return id;
    }
    public Integer getAvailableStock() {
        return availableStock;
    }
    public Integer getAllocatedAmount() {
        return allocatedAmount;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setAvailableStock(Integer availableStock) {
        this.availableStock = availableStock;
    }
    public void setAllocatedAmount(Integer allocatedAmount) {
        this.allocatedAmount = allocatedAmount;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", availableStocks=" + availableStock +
                ", allocatedAmount=" + allocatedAmount +
                '}';
    }
}
