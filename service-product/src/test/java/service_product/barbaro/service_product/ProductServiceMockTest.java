package service_product.barbaro.service_product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import service_product.barbaro.service_product.entity.Category;
import service_product.barbaro.service_product.entity.Product;
import service_product.barbaro.service_product.repository.ProductRepository;
import service_product.barbaro.service_product.service.ProductService;
import service_product.barbaro.service_product.service.ProductServiceImpl;

import java.util.Date;
import java.util.Optional;

@SpringBootTest
public class ProductServiceMockTest {

    @Mock
    private ProductRepository productRepository;

    private ProductService productService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        productService = new ProductServiceImpl(productRepository);
        Product product = Product.builder()
                .name("pc")
                .price(10.55)
                .status("CREATED")
                .description("")
                .category(Category.builder().name("Computadoras").build())
                .stock(2)
                .createdAt(new Date())
                .build();
        Mockito.when(productRepository.findById(Mockito.any())).thenReturn(Optional.of(product));
        Mockito.when(productRepository.save(product)).thenReturn(product);

    }

    @Test
    public void updateStockMockTest() {

        Product productDB = productRepository.findById(1L).orElse(null);

        assert productDB != null;
        Product newProduct = productService.updateStock(productDB.getId(), 5.0);

        Assertions.assertThat(newProduct.getStock()).isEqualTo(7);
    }
}
