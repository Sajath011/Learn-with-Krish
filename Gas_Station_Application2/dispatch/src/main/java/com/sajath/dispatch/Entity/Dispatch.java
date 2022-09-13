package com.sajath.dispatch.Entity;

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
@Entity(name = "Dispatch")
public class Dispatch {
    @Id
    @SequenceGenerator(
            name = "dispatch_id_sequence",
            sequenceName = "dispatch_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "dispatch_id_sequence"
    )
    private Integer dispatchId;

    private Integer orderId;

    @Column(name = "status")
    private String status;

    @Column(name = "allocAmount")
    private Integer allocAmount;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "dispatchAt")
    private LocalDateTime dispatchAt;

    @Column(name = "isDispatched")
    private Boolean isDispatched;
}
