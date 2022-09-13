package com.sajath.schedule.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AllocationCheckHistory {
    @Id
    private Long id;
    private Integer orderId;

    @Column(name = "allocAmmount")
    private Integer allocAmmount;

    @Column(name = "status")
    private String status;

    @Column(name = "isStockAvailable")
    private Boolean isStockAvailable;

    @Column(name = "createdAt")
    private ArrayList<Integer> createdAt;

}
