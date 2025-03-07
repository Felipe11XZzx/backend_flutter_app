package com.example.demo.api;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.models.Orders;
import com.example.demo.models.requests.OrdersCreationRequest;
import com.example.demo.services.OrdersServices;
    
@RestController
@RequestMapping("api/v1/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final OrdersServices orderService;

    public OrderController(OrdersServices ordersService){
        this.orderService = ordersService;
    }

    @GetMapping("/getallorders")
    public List<Orders> getAllOrders(){
        logger.info("Obteniendo todas las órdenes");
        return orderService.getAllOrders();
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrdersCreationRequest ordersCreationRequest){
        logger.info("Creando orden: {}", ordersCreationRequest);
        
        // Validar que numeroPedido no sea nulo
        if (ordersCreationRequest.numeroPedido() == null) {
            logger.error("Error: numeroPedido es nulo");
            return ResponseEntity.badRequest().body("El número de pedido no puede ser nulo");
        }
        
        // Validar que descripcion no sea nula o vacía
        if (ordersCreationRequest.descripcion() == null || ordersCreationRequest.descripcion().isEmpty()) {
            logger.error("Error: descripción es nula o vacía");
            return ResponseEntity.badRequest().body("La descripción no puede estar vacía");
        }
        
        try {
            Orders createdOrder = orderService.createOrder(ordersCreationRequest);
            logger.info("Orden creada con éxito: {}", createdOrder);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
        } catch (Exception e) {
            logger.error("Error al crear la orden: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear la orden: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable String id){
        logger.info("Obteniendo orden con id: {}", id);
        return orderService.getOrder(id)
                .map(order -> ResponseEntity.ok(order))
                .orElseGet(() -> {
                    logger.warn("Orden con id {} no encontrada", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable String id, @RequestBody Orders order){
        logger.info("Actualizando orden con id: {}", id);
        
        // Validar que numeroPedido no sea nulo
        if (order.getNumeroPedido() == null) {
            logger.error("Error: numeroPedido es nulo");
            return ResponseEntity.badRequest().body("El número de pedido no puede ser nulo");
        }
        
        try {
            Orders updatedOrder = orderService.updateOrder(order);
            if (updatedOrder != null) {
                logger.info("Orden actualizada con éxito: {}", updatedOrder);
                return ResponseEntity.ok(updatedOrder);
            } else {
                logger.warn("No se pudo actualizar la orden con id: {}", id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Error al actualizar la orden: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar la orden: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeOrder(@PathVariable String id){
        logger.info("Eliminando orden con id: {}", id);
        try {
            orderService.removeOrder(id);
            logger.info("Orden eliminada con éxito");
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Error al eliminar la orden: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
