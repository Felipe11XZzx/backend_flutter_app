package com.example.demo.models.requests;

public record UserCreationRequest(String nombre, String contrasena, int edad, boolean administrador) {
    
}
