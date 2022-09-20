package com.leanicontechnology.SpringBootAssessment.api.service.services;

import com.leanicontechnology.SpringBootAssessment.api.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto createNewProduct(ProductDto product);

    List<ProductDto> getAllProducts();

    ProductDto getOneProduct(String productUUID);

    ProductDto updateProduct(String productUUID, ProductDto product);

    ProductDto deleteProduct(String productUUID);


}
