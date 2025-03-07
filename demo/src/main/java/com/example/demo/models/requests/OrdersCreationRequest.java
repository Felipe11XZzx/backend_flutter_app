package com.example.demo.models.requests;

public record OrdersCreationRequest(
    Integer numeroPedido,
    String descripcion,
    Double precio,
    String estado,
    String comprador,
    String detalleProductos
) {
    
}
