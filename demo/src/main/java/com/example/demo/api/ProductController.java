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

    @DeleteMapping
    public void removeProduct(@PathVariable Long id){
        productService.removeProduct(id);
    }

    @GetMapping
    public Product getProduct(@PathVariable Long id){
        return productService.getProduct(id).orElse(null);
    }

    @GetMapping("/getallproducts")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
}
