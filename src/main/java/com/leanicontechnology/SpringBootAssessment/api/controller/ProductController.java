package com.leanicontechnology.SpringBootAssessment.api.controller;

import com.leanicontechnology.SpringBootAssessment.api.dto.ProductDto;
import com.leanicontechnology.SpringBootAssessment.api.service.services.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/products")
@Slf4j
@AllArgsConstructor
public class ProductController {

    ProductService productService;

    @GetMapping(path = "/")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping(path = "/{productUUID}")
    public ResponseEntity<ProductDto> getProductByUniqueID(@PathVariable String productUUID) {
        return new ResponseEntity<>(productService.getOneProduct(productUUID), HttpStatus.OK);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto product) {
        return new ResponseEntity<>(productService.createNewProduct(product), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{productUUID}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable String productUUID,
                                                    @RequestBody ProductDto product) {
        return new ResponseEntity<>(productService.updateProduct(productUUID, product), HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{productUUID}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable String productUUID) {
        return new ResponseEntity<>(productService.deleteProduct(productUUID), HttpStatus.OK);
    }
}
