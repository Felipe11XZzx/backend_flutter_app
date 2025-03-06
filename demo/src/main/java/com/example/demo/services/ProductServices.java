package com.example.demo.services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.example.demo.models.requests.ProductCreationRequest;
import com.example.demo.models.Product;
import com.example.demo.repository.ProductRepository;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServices {
    private static final Logger logger = LoggerFactory.getLogger(ProductServices.class);
    private final ProductRepository productRepository;

    public ProductServices(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public Product createProduct(ProductCreationRequest prodCreationRequest){
        return productRepository.save(mapToProduct(prodCreationRequest));
    }

    public Product updateProduct(final Product IDproduct){
        try {
            return productRepository.save(IDproduct);
        } catch (Exception e) {
            logger.error("Error recuperando el producto con id {}, Exception {}", IDproduct,e);
        }
        return null;
    }

    public void removeProduct(String id){
        productRepository.deleteById(id);
    }

    public Optional<Product> getProduct(final String id){
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product mapToProduct(ProductCreationRequest prodCreationRequest){
        Product producto = new Product();
        producto.setNombreProducto(prodCreationRequest.nombre());
        producto.setDescripcion(prodCreationRequest.descripcion());
        producto.setPrecio(prodCreationRequest.precio());
        producto.setCantidad(prodCreationRequest.cantidad());
        producto.setImagenProducto(prodCreationRequest.imagenProducto());
        return producto;
    }
}
