package com.example.demo.services;
import org.springframework.stereotype.Service;
import com.example.demo.models.requests.ProductCreationRequest;
import com.example.demo.models.Product;
import com.example.demo.repository.ProductRepository;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServices {
    private final ProductRepository productRepository;

    public ProductServices(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public Product createProduct(ProductCreationRequest prodCreationRequest){
        return productRepository.save(mapToProduct(prodCreationRequest));
    }

    public void removeProduct(Long id){
        productRepository.deleteById(id);
    }

    public Optional<Product> getProduct(final long id){
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product mapToProduct(ProductCreationRequest prodCreationRequest){
        Product producto = new Product();
        producto.setNombreProducto(prodCreationRequest.nombreProducto());
        producto.setDescripcion(prodCreationRequest.descripcion());
        producto.setPrecio(prodCreationRequest.precio());
        producto.setStock(prodCreationRequest.stock());
        return producto;
    }
}
