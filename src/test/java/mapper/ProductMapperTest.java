package mapper;

import com.telran.bankingapp.dto.ProductDTO;
import com.telran.bankingapp.entity.Product;
import com.telran.bankingapp.mapper.ProductMapperImpl;
import com.telran.bankingapp.mapper.ProductMapper;
import util.DtoCreator;
import util.EntityCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Product Mapper test class")
class ProductMapperTest {

    private final ProductMapper productMapper = new ProductMapperImpl();
    private final Product product = EntityCreator.getProduct();
    private final ProductDTO productDto = DtoCreator.getProductDto();

//    @Test
//    void fromEntityToProductDtoTest() {
//        Assertions.assertEquals(productDto, productMapper.toDTO(product));
//    }
//
//    @Test
//    void fromProductDtoToEntityTest() {
//        Assertions.assertEquals(product, productMapper.toProduct(productDto));
//    }
}