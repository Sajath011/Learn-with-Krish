package com.sajath.allocation.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

// Allocation Class where we define our variables of the class
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "AllocationCheck")
public class CheckAllocationHistory {
    @Id
    @SequenceGenerator(
            name = "allocation_id_sequence",
            sequenceName = "allocation_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "allocation_id_sequence"
    )
    private Long id;

    @Column(name = "orderId")
    private Integer orderId;

    @Column(name = "allocAmmount")
    private Integer allocAmmount;

    @Column(name = "status")
    private String status;

    @Column(name = "isStockAvailable")
    private Boolean isStockAvailable;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

}
