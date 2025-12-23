package com.mdev.supermarket_api.controller;

import com.mdev.supermarket_api.dto.ProductDTO;
import com.mdev.supermarket_api.service.interfaces.IProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final IProductService productServ;
    public ProductController(IProductService productService){
        this.productServ = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        return ResponseEntity.ok(productServ.getAllProducts());
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO dto){
        ProductDTO product = productServ.createProduct(dto);
        return ResponseEntity.created(URI.create("/api/products" + product.getId())).body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO dto){
        return ResponseEntity.ok(productServ.updateProduct(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        productServ.deleteProductById(id);
        return ResponseEntity.ok("Producto eliminado correctamente de la Base de Datos.");
    }
}
