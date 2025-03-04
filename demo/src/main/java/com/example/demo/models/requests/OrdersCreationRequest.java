package com.example.demo.models.requests;

public record OrdersCreationRequest(Long id, int numeroOrden, String descripcion, Double precio, String estado, String comprador) {
    
}
