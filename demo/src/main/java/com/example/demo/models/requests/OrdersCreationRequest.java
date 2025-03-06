package com.example.demo.models.requests;

public record OrdersCreationRequest(
    int numeroPedido,
    String descripcion,
    Double precio,
    String estado,
    String comprador,
    String detalleProductos
) {
    
}
