package com.sajath.order.Service;

import com.sajath.order.Entity.Order;
import com.sajath.order.OrderRequest;
import com.sajath.order.Repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    KafkaTemplate<String, Order> kafkaTemplate;
    public void placeAnOrder(OrderRequest request) {

        Order orderBuilder = Order.builder()
                .status("Order has been created")
                .createdAt(LocalDateTime.now())
                .allocAmount(request.allocAmount())
                .build();

        Message<Order> msgBuilder = MessageBuilder.withPayload(orderBuilder)
                .setHeader(KafkaHeaders.TOPIC,"mainTopic")
                        .build();

        orderRepository.save(orderBuilder);

        kafkaTemplate.send(msgBuilder);
    }
}
