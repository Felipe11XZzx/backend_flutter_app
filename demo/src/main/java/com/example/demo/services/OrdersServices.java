package com.example.demo.services;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import com.example.demo.models.requests.OrdersCreationRequest;
import com.example.demo.repository.OrdersRepository;
import com.example.demo.models.Orders;
import java.util.List;
import java.util.Optional;
import java.time.Instant;

@Service
public class OrdersServices {
    
    private static final Logger logger = LoggerFactory.getLogger(OrdersServices.class);
    private final OrdersRepository orderRepository;

    public OrdersServices(OrdersRepository ordersRepository){
        this.orderRepository = ordersRepository;
    }

    public Orders createOrder(OrdersCreationRequest ordersCreationRequest){
        logger.info("Creando orden con datos: {}", ordersCreationRequest);
        
        // Validar y asegurar que numeroPedido no sea nulo
        Integer numeroPedido = ordersCreationRequest.numeroPedido();
        if (numeroPedido == null || numeroPedido == 0) {
            // Generar un número de pedido basado en el timestamp actual
            numeroPedido = (int) (Instant.now().toEpochMilli() % 900000 + 100000);
            logger.warn("numeroPedido era nulo o cero, generando uno nuevo: {}", numeroPedido);
        }
        
        Orders order = maptoOrders(ordersCreationRequest);
        
        // Asegurar que numeroPedido esté establecido
        order.setNumeroPedido(numeroPedido);
        
        // Asegurar que detalleProductos no sea nulo
        if (order.getDetalleProductos() == null) {
            order.setDetalleProductos("{}");
            logger.warn("detalleProductos era nulo, estableciendo un objeto JSON vacío");
        }
        
        logger.info("Guardando orden: {}", order);
        return orderRepository.save(order);
    }

    public void removeOrder(String id){
        logger.info("Eliminando orden con id: {}", id);
        orderRepository.deleteById(id);
    }

    public Orders updateOrder(Orders order){
        logger.info("Actualizando orden: {}", order);
        
        // Validar y asegurar que numeroPedido no sea nulo
        if (order.getNumeroPedido() == null || order.getNumeroPedido() == 0) {
            // Generar un número de pedido basado en el timestamp actual
            int numeroPedido = (int) (Instant.now().toEpochMilli() % 900000 + 100000);
            order.setNumeroPedido(numeroPedido);
            logger.warn("numeroPedido era nulo o cero, generando uno nuevo: {}", numeroPedido);
        }
        
        // Asegurar que detalleProductos no sea nulo
        if (order.getDetalleProductos() == null) {
            order.setDetalleProductos("{}");
            logger.warn("detalleProductos era nulo, estableciendo un objeto JSON vacío");
        }
        
        try {
            Orders savedOrder = orderRepository.save(order);
            logger.info("Orden actualizada con éxito: {}", savedOrder);
            return savedOrder;
        } catch (Exception e) {
            logger.error("Error actualizando la orden con id {}, Exception: {}", order.getId(), e.getMessage(), e);
            throw e;
        }
    }
    
    public Optional<Orders> getOrder(final String id){
        logger.info("Obteniendo orden con id: {}", id);
        return orderRepository.findById(id);
    }

    public List<Orders> getAllOrders(){
        logger.info("Obteniendo todas las órdenes");
        return orderRepository.findAll();
    }

    public Orders maptoOrders(OrdersCreationRequest ordersCreationRequest){
        logger.debug("Mapeando OrdersCreationRequest a Orders: {}", ordersCreationRequest);
        
        Orders order = new Orders();
        
        // Asegurar que numeroPedido no sea nulo
        Integer numeroPedido = ordersCreationRequest.numeroPedido();
        if (numeroPedido == null || numeroPedido == 0) {
            // Generar un número de pedido basado en el timestamp actual
            numeroPedido = (int) (Instant.now().toEpochMilli() % 900000 + 100000);
            logger.warn("numeroPedido era nulo o cero, generando uno nuevo: {}", numeroPedido);
        }
        order.setNumeroPedido(numeroPedido);
        
        order.setDescripcion(ordersCreationRequest.descripcion());
        order.setPrecio(ordersCreationRequest.precio());
        order.setEstado(ordersCreationRequest.estado());
        order.setComprador(ordersCreationRequest.comprador());
        
        // Asegurar que detalleProductos no sea nulo
        String detalleProductos = ordersCreationRequest.detalleProductos();
        if (detalleProductos == null) {
            detalleProductos = "{}";
            logger.warn("detalleProductos era nulo, estableciendo un objeto JSON vacío");
        }
        order.setDetalleProductos(detalleProductos);
        
        logger.debug("Orden mapeada: {}", order);
        return order;
    }
}
