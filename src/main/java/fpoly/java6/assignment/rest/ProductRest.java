package fpoly.java6.assignment.rest;

import fpoly.java6.assignment.model.Product;
import fpoly.java6.assignment.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "${server.cors}")
public class ProductRest {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Product product = this.productService.findById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.ok(this.productService.findAll());
    }

    @PostMapping
    public ResponseEntity searchByKey(@RequestBody String keyword) {
        return ResponseEntity.ok(this.productService.findByName(keyword));
    }
}
