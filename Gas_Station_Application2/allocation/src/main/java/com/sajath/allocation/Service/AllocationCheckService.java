package com.sajath.allocation.Service;

import com.sajath.allocation.Entity.CheckAllocationHistory;
import com.sajath.allocation.Entity.Order;
import com.sajath.allocation.Entity.Stock;
import com.sajath.allocation.Repository.CheckAllocationHistoryRepository;
import com.sajath.allocation.Repository.StockRepository;
import com.google.gson.Gson;
import com.sajath.allocation.UpdateStockMessage;
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
public class AllocationCheckService {

    private final CheckAllocationHistoryRepository allocationCheckHistoryRepository;
    private final StockRepository stockRepository;


    KafkaTemplate<String, CheckAllocationHistory> kafkaTemplate;

    @KafkaListener(topics = "mainTopic", groupId = "groupId")
    public void listenerForOrder (String data) {
        Gson gson = new Gson();
        Order order = gson.fromJson(data,Order.class);


        List<Stock> byIdDESC = stockRepository.findByIdDESC();
        Stock stock = byIdDESC.get(0);

        Integer alreadyAllocatedAmount = stock.getAllocatedAmount();
        Integer availableStock = stock.getAvailableStock();
        Integer allocAmount = order.getAllocAmount();
        Boolean checkStock = checkStock(availableStock,allocAmount,alreadyAllocatedAmount);

        System.out.println("Listener received json: " + order);
        CheckAllocationHistory checkAllocationHistory = CheckAllocationHistory
                .builder()
                .orderId(order.getOrderId())
                .allocAmmount(order.getAllocAmount())
                .status(checkStatus(checkStock))
                .createdAt(LocalDateTime.now())
                .isStockAvailable(checkStock)
                .build();
        allocationCheckHistoryRepository.save(checkAllocationHistory);

        Message<CheckAllocationHistory> message = MessageBuilder.withPayload(checkAllocationHistory)
                .setHeader(KafkaHeaders.TOPIC,"secondTopic")
                .build();
        kafkaTemplate.send(message);
    }

    public void stockUpdate(UpdateStockMessage updateStockmessage){
        List<Stock> st = stockRepository.findByIdDESC();

  if (st.isEmpty()){
            Stock stock = Stock.builder()
                    .allocatedAmount(updateStockmessage.allocatedAmount())
                    .availableStock(updateStockmessage.availableStocks())
                    .build();
            stockRepository.save(stock);
        }else{
            Stock stock1 = st.get(0);

            Integer alreadyAllocatedAmount = stock1.getAllocatedAmount();
            Integer availableStock = stock1.getAvailableStock();

            Integer totalStock = availableStock + updateStockmessage.availableStocks();
            Integer totalAllocatedAmount = alreadyAllocatedAmount + updateStockmessage.allocatedAmount();

            Stock stock = Stock.builder()
                    .allocatedAmount(totalAllocatedAmount)
                    .availableStock(totalStock)
                    .build();
            stockRepository.save(stock);
        }
    }

    public Boolean checkStock(Integer availableStocks,Integer allocatedStocks, Integer alreadyAllocatedStock){
        if (availableStocks >=  allocatedStocks){
            Integer balanceStock = availableStocks-allocatedStocks;
            Integer TotalAllocation = alreadyAllocatedStock+allocatedStocks;
            Stock stock = Stock.builder()
                    .allocatedAmount(TotalAllocation)
                    .availableStock(balanceStock)
                    .build();
            stockRepository.save(stock);
            return true;
        }
        else
            return false;
    }
    public String checkStatus(Boolean checkStock){
        if (checkStock){
            return "Order is Allocated";
        }else {
            return "Out Of Stock";
        }
    }
}
