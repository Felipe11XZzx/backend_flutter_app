package com.example.demo.models.requests;

public record ProductCreationRequest(Long id, String nombreProducto, String descripcion, Double precio, int stock) {
    
}
