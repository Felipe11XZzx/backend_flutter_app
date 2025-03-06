package com.example.demo.services;
import org.springframework.stereotype.Service;
import com.example.demo.models.requests.OrdersCreationRequest;
import com.example.demo.repository.OrdersRepository;
import com.example.demo.models.Orders;
import java.util.List;
import java.util.Optional;

@Service
public class OrdersServices {
    
    private final OrdersRepository orderRepository;

    public OrdersServices(OrdersRepository ordersRepository){
        this.orderRepository = ordersRepository;
    }

    public Orders createOrder(OrdersCreationRequest ordersCreationRequest){
        return orderRepository.save(maptoOrders(ordersCreationRequest));
    }

    public void removeOrder(Long id){
        orderRepository.deleteById(id);
    }

    public Orders updateOrder(Orders order){
        return orderRepository.save(order);
    }
    
    public Optional<Orders> getOrder(final long id){
        return orderRepository.findById(id);
    }

    public List<Orders> getAllOrders(){
        return orderRepository.findAll();
    }

    public Orders maptoOrders(OrdersCreationRequest ordersCreationRequest){
        Orders order = new Orders();
        order.setNumeroPedido(ordersCreationRequest.numeroPedido());
        order.setDescripcion(ordersCreationRequest.descripcion());
        order.setPrecio(ordersCreationRequest.precio());
        order.setEstado(ordersCreationRequest.estado());
        order.setComprador(ordersCreationRequest.comprador());
        return order;
    }
}
