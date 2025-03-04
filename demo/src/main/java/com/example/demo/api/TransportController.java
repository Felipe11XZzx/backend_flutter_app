package com.example.demo.api;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.example.demo.models.Transport;
import com.example.demo.repository.TransportRepository;


@RestController
@RequestMapping("/api/v1/transports")
@CrossOrigin(origins = "*")
public class TransportController {
    
    private final TransportRepository transportRepository;

    public TransportController(TransportRepository transportRepository){
        this.transportRepository = transportRepository;
    }

    @GetMapping
    public List<Transport> obtenerTransportes(){
        return transportRepository.findAll();
    }

    @PostMapping
    public Transport crearTransporte(@RequestBody Transport transport){
        return transportRepository.save(transport);
    }

    @GetMapping("/category/{categoryId}")
    public List<Transport> obtenerTransportesPorCategoria(@PathVariable String categoryId){
        return transportRepository.findByCategory(categoryId);
    }

    @GetMapping("/license/{license}")
    public List<Transport> obtenerTransportesPorLicencia(@PathVariable String license){
        return transportRepository.findByLicense(license);
    }

    @DeleteMapping("/{id}")
    public void eliminarTransporte(@PathVariable String id){
        transportRepository.deleteById(id);
    }
}
