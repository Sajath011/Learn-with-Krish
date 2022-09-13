package com.sajath.dispatch.Entity;

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
public class Schedule {
    @Id
    private Integer scheduleId;

    private Integer orderId;

    @Column(name = "allocAmount")
    private Integer allocAmount;

    @Column(name = "status")
    private String status;

    @Column(name = "createdAt")
    private ArrayList<Integer> createdAt;

    @Column(name = "scheduleTime")
    private ArrayList<Integer>  scheduleTime;
}
