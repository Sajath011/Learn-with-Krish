package com.sajath.allocation.Controller;

import com.sajath.allocation.Entity.Stock;
import com.sajath.allocation.Repository.StockRepository;
import com.sajath.allocation.Service.AllocationCheckService;
import com.sajath.allocation.UpdateStockMessage;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/allocation-check")
@AllArgsConstructor
public class AllocationController {

    private final StockRepository stockRepository;
    private final AllocationCheckService allocationCheckService;

    @CrossOrigin(origins = "http://localhost:4201")
    @PostMapping("/update-stock")
    public void updatedStockAdmin(@RequestBody UpdateStockMessage updateStockmessage){
        allocationCheckService.stockUpdate(updateStockmessage);
    }

    @CrossOrigin(origins = "http://localhost:4201")
    @GetMapping("/get-all")
    public List<Stock> getOrdersByIDDesc(){
        return stockRepository.findByIdDESC();
    }

}
