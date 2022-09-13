package com.sajath.order.Controller;

import com.sajath.order.Entity.Order;
import com.sajath.order.Repository.OrderRepository;
import com.sajath.order.OrderRequest;
import com.sajath.order.Service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderService orderService;


    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/data")
    public void placeAnOrder(@RequestBody OrderRequest orderRequest){
        log.info("New Order Placed {}",orderRequest);
        orderService.placeAnOrder(orderRequest);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/get-all")
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

}
