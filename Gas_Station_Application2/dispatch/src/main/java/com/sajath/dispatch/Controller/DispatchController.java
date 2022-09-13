package com.sajath.dispatch.Controller;

import com.sajath.dispatch.DispatchOrderRequest;
import com.sajath.dispatch.Entity.Dispatch;
import com.sajath.dispatch.Repository.DispatchRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/dispatch")
@AllArgsConstructor
public class DispatchController {
    private final DispatchRepository dispatchRepository;

    @CrossOrigin(origins = "http://localhost:4201")
    @PostMapping("/update-dispatch")
    public void placeAnOrder(@RequestBody DispatchOrderRequest dispatchOrderRequest){
        log.info("New Order Placed {}",dispatchOrderRequest.id());
        Integer id = dispatchOrderRequest.id();
        Boolean isDispatch = dispatchOrderRequest.isDispatch();
        Dispatch dispatchRepositoryOne = dispatchRepository.getOne(id);
        if(!isDispatch){
            dispatchRepositoryOne.setStatus("Order has been Dispatched");
            dispatchRepositoryOne.setIsDispatched(true);
            dispatchRepository.save(dispatchRepositoryOne);
        }

        log.info("New Order Placed {}",id);
    }

    @CrossOrigin(origins = "http://localhost:4201")
    @GetMapping("/get-all")
    public List<Dispatch> getAllOrders(){
        return dispatchRepository.findAllByIdDESC();
    }
}
