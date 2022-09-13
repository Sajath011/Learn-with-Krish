package com.sajath.schedule.Entity;

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
@Entity(name = "Schedule")
public class Schedule {
    @Id
    @SequenceGenerator(
            name = "schedule_id_sequence",
            sequenceName = "schedule_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "schedule_id_sequence"
    )
    private Integer scheduleId;
    private Integer orderId;

    @Column(name = "allocAmount")
    private Integer allocAmount;

    @Column(name = "status")
    private String status;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "scheduleTime")
    private LocalDateTime scheduleTime;
}
