package com.telran.bankingapp.mapper;

import com.telran.bankingapp.dto.ProductDTO;
import com.telran.bankingapp.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PorductMapper {
    ProductDTO toDTO(Product product);

    Product toProduct(ProductDTO productDTO);
}
