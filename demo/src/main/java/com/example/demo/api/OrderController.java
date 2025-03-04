package com.example.demo.api;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.example.demo.models.Orders;
import com.example.demo.models.requests.OrdersCreationRequest;
import com.example.demo.services.OrdersServices;
    
    @RestController
    @RequestMapping("api/v1/orders")
    @CrossOrigin(origins = "*")
    public class OrderController {

        private final OrdersServices orderService;

        public OrderController(OrdersServices ordersService){
            this.orderService = ordersService;
        }

        @PostMapping
        public Orders createOrder(@RequestBody OrdersCreationRequest ordersCreationRequest){
            return orderService.createOrder(ordersCreationRequest);
        }

        @DeleteMapping
        public void remoceOrder(@PathVariable Long id){
            orderService.removeOrder(id);
        }

        @GetMapping("/{id}")
        public Orders getOrder(@PathVariable long id){
            return orderService.getOrder(id).orElse(null);
        }

        @GetMapping
        public List<Orders> getAllOrders(){
            return orderService.getAllOrders();
        }
}
