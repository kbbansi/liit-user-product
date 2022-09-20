package com.leanicontechnology.SpringBootAssessment.api.service.impl;

import com.leanicontechnology.SpringBootAssessment.api.dto.ProductDto;
import com.leanicontechnology.SpringBootAssessment.api.service.services.ProductService;
import com.leanicontechnology.SpringBootAssessment.data.entity.ProductEntity;
import com.leanicontechnology.SpringBootAssessment.data.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    ModelMapper mapper = new ModelMapper();

    @Autowired
    ProductRepository productRepository;


    /**
     * @param product  product to be created
     * @return - created product
     */
    @Override
    public ProductDto createNewProduct(ProductDto product) {
        log.info("Create Product Service; Request Body: [{}]", product);
        var productAlreadyExists = productRepository.findByProductName(product.getProductName())
                .stream()
                .map(productEntity -> productEntity.getProductName().equals(product.getProductName()))
                .findAny()
                .orElse(Boolean.FALSE);

        if (!productAlreadyExists) {
            var productEntity = ProductEntity.builder()
                    .productName(product.getProductName())
                    .productUUID(UUID.randomUUID().toString())
                    .description(product.getDescription())
                    .unitPrice(product.getUnitPrice())
                    .createdOn(LocalDateTime.now())
                    .isActive(true)
                    .build();
            return mapper.map(productRepository.save(productEntity), ProductDto.class);
        }
        throw new RuntimeException("Product with name: " + product.getProductName() + " already exists");
    }

    /**
     * @return list of all products
     */
    @Override
    public List<ProductDto> getAllProducts() {
        log.info("Get All Products Service");
        return productRepository.findAll()
                .stream()
                .map(product -> mapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    /**
     * @param productUUID product uuid
     * @return product
     */
    @Override
    public ProductDto getOneProduct(String productUUID) {
        var productEntity = productRepository.findByProductUUID(productUUID)
                .orElseThrow(()-> new RuntimeException("No such product found with ID: " + productUUID));
        return mapper.map(productEntity, ProductDto.class);
    }

    /**
     * @param productUUID product uuid to be updated
     * @param product update request object
     * @return - updated request
     */
    @Override
    public ProductDto updateProduct(String productUUID, ProductDto product) {
        log.info("Update Product Service; Request Body: [{}]", product);
        var productEntity = productRepository.findByProductUUID(productUUID)
                .orElseThrow(()-> new RuntimeException("No such product found with ID: " + productUUID));
        productEntity.setProductName(product.getProductName());
        productEntity.setDescription(product.getDescription());
        productEntity.setUnitPrice(product.getUnitPrice());
        productEntity.setModifiedOn(LocalDateTime.now());

        return mapper.map(productRepository.save(productEntity), ProductDto.class);
    }

    /**
     * @param productUUID product uuid to be deleted
     * @return deleted product
     */
    @Override
    public ProductDto deleteProduct(String productUUID) {
        var productEntity = productRepository.findByProductUUID(productUUID)
                .orElseThrow(()-> new RuntimeException("No such product found with ID: " + productUUID));

        productEntity.setIsActive(false);
        return mapper.map(productRepository.save(productEntity), ProductDto.class);
    }
}
