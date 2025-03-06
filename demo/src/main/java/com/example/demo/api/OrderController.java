package com.example.demo.api;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.example.demo.models.Orders;
import com.example.demo.models.requests.OrdersCreationRequest;
import com.example.demo.services.OrdersServices;
    
@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class OrderController {

    private final OrdersServices orderService;

    public OrderController(OrdersServices ordersService){
        this.orderService = ordersService;
    }

    @GetMapping
    public List<Orders> getAllOrders(){
        return orderService.getAllOrders();
    }

    @PostMapping
    public Orders createOrder(@RequestBody OrdersCreationRequest ordersCreationRequest){
        return orderService.createOrder(ordersCreationRequest);
    }

    @GetMapping("/{id}")
    public Orders getOrder(@PathVariable long id){
        return orderService.getOrder(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Orders updateOrder(@PathVariable long id, @RequestBody Orders order){
        return orderService.updateOrder(order);
    }

    @DeleteMapping("/{id}")
    public void removeOrder(@PathVariable Long id){
        orderService.removeOrder(id);
    }
}
