package com.example.demo.models.requests;

public record ProductCreationRequest(String nombre, String descripcion, Double precio, int cantidad, String imagenProducto) {
    
}
