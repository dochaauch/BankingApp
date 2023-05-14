package mapper.mapperImpl;

import com.telran.bankingapp.dto.ProductDTO;
import com.telran.bankingapp.entity.Product;
import com.telran.bankingapp.mapper.ProductMapper;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDTO toDTO(Product product) {
        if (product == null) {
            return null;
        } else {
            String id = null;
            String name;
            if (product.getId() != null) {
                id = product.getId().toString();
            }

            name = product.getName();
            ProductDTO productDTO = new ProductDTO(id, name);
            return productDTO;
        }
    }

    @Override
    public Product toProduct(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        } else {
            Product product = new Product();
            if (productDTO.getId() != null) {
                product.setId(UUID.fromString(productDTO.getId()));
            }

            product.setName(productDTO.getName());
            return product;
        }
    }
}