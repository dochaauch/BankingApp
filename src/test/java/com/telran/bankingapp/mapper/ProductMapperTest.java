package com.telran.bankingapp.mapper;

import com.telran.bankingapp.dto.ProductDTO;
import com.telran.bankingapp.entity.Product;
import com.telran.bankingapp.util.DtoCreator;
import com.telran.bankingapp.util.EntityCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProductMapperTest {

    private final ProductMapper productMapper = new ProductMapperImpl();

    @Test
    void testToDTO() {
        // Create a Product object for testing
        Product product = EntityCreator.getProductEntity();
        System.out.println(product);

        // Call the toDTO method
        ProductDTO productDTO = productMapper.toDTO(product);

        // Assert that the DTO object is correctly mapped
        Assertions.assertEquals("aaf2bc95-1403-4831-b8b5-8969445548ec", productDTO.getId());
        Assertions.assertNull(productDTO.getName());
    }

    @Test
    void testToProduct() {
        // Create a ProductDTO object for testing
        ProductDTO productDTO = DtoCreator.getProductDto();
        System.out.println(productDTO);

        // Create a Product object for testing
        Product product = EntityCreator.getProductEntity();
        System.out.println(product);

        // Call the toProduct method
        Product product1 = productMapper.toProduct(productDTO);

        // Assert that the Product object is correctly mapped
        Assertions.assertEquals("aaf2bc95-1403-4831-b8b5-8969445548ec", product1.getId().toString());
        Assertions.assertEquals("Savings Account", product1.getName());
    }
}