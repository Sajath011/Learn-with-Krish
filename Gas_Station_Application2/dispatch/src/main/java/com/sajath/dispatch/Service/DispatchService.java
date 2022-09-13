package com.sajath.dispatch.Service;

import com.sajath.dispatch.Entity.Dispatch;
import com.sajath.dispatch.Entity.Schedule;
import com.google.gson.Gson;
import com.sajath.dispatch.Repository.DispatchRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class DispatchService {
    private final DispatchRepository dispatchRepository;

    @KafkaListener(topics = "thirdTopic", groupId = "groupId")
    public void orderDispatch(String data){
        Gson gSon = new Gson();
        Schedule schedule = gSon.fromJson(data,Schedule.class);

        List<Integer> scheduledDate = schedule.getScheduleTime();
        LocalDateTime scheduledTime =LocalDateTime.of(scheduledDate.get(0),scheduledDate.get(1),scheduledDate.get(2), scheduledDate.get(3),scheduledDate.get(4),scheduledDate.get(5),scheduledDate.get(6));
        System.out.print(scheduledTime + "\n");
        Boolean isDispatched = isDispatched(scheduledTime);

        Dispatch dispatchBuilder = Dispatch.builder()
                .orderId(schedule.getOrderId())
                .allocAmount(schedule.getAllocAmount())
                .createdAt(LocalDateTime.now())
                .dispatchAt(scheduledTime)
                .isDispatched(isDispatched)
                .status(checkDispatchStatus(isDispatched))
                .build();
        dispatchRepository.save(dispatchBuilder);
    }
    public Boolean isDispatched(LocalDateTime scheduledTime){
        if (LocalDateTime.now().equals(scheduledTime)){
            return true;
        }else {
            return false;
        }
    }

    public String checkDispatchStatus(Boolean isDispatched){
        if (isDispatched){
            return "Order has been Dispatched";
        }else {
            return "Order has not been Dispatched";
        }
    }
}
