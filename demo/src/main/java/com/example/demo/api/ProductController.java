package com.example.demo.api;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.example.demo.models.Product;
import com.example.demo.models.requests.ProductCreationRequest;
import com.example.demo.services.ProductServices;

@RestController
@RequestMapping("api/v1/products")
@CrossOrigin(origins = "*")
public class ProductController {
    
    private final ProductServices productService;

    public ProductController(ProductServices productService){
        this.productService = productService;
    }

    @PostMapping
    public Product createProduct(@RequestBody ProductCreationRequest productCreationRequest){
        return productService.createProduct(productCreationRequest);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product){
        return productService.updateProduct(product);
    }

    @DeleteMapping("/{id}")
    public void removeProduct(@PathVariable String id){
        productService.removeProduct(id);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable String id){
        return productService.getProduct(id).orElse(null);
    }

    @GetMapping("/getallproducts")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
}
