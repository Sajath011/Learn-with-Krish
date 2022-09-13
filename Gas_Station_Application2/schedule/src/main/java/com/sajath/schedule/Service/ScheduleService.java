package com.sajath.schedule.Service;

import com.sajath.schedule.Entity.AllocationCheckHistory;
import com.sajath.schedule.Entity.Schedule;
import com.google.gson.*;
import com.sajath.schedule.Repository.ScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    KafkaTemplate<String, Schedule> kafkaTemplate;

    @KafkaListener(topics = "secondTopic", groupId = "groupId")
    public void scheduleOrder(String data){

        Gson gSon = new Gson();
        AllocationCheckHistory allocationCheckHistory = gSon.fromJson(data,AllocationCheckHistory.class);


        List<Integer> historyCreatedAt = allocationCheckHistory.getCreatedAt();
        LocalDateTime createdAt =LocalDateTime.of(historyCreatedAt.get(0),historyCreatedAt.get(1),historyCreatedAt.get(2), historyCreatedAt.get(3),historyCreatedAt.get(4),historyCreatedAt.get(5),historyCreatedAt.get(6));
        System.out.println(createdAt);

        Schedule scheduleBuilder = Schedule.builder()
                .orderId(allocationCheckHistory.getOrderId())
                .allocAmount(allocationCheckHistory.getAllocAmmount())
                .status("Order has been scheduled")
                .createdAt(createdAt)
                .scheduleTime(LocalDateTime.now().plusDays(5))
                .build();
        scheduleRepository.save(scheduleBuilder);

        Message<Schedule> msgBuilder = MessageBuilder.withPayload(scheduleBuilder)
                .setHeader(KafkaHeaders.TOPIC,"thirdTopic")
                .build();
        kafkaTemplate.send(msgBuilder);
    }
}
