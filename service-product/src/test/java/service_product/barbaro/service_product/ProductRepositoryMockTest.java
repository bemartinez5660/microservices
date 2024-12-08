package service_product.barbaro.service_product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import service_product.barbaro.service_product.entity.Category;
import service_product.barbaro.service_product.entity.Product;
import service_product.barbaro.service_product.repository.CategoryRepository;
import service_product.barbaro.service_product.repository.ProductRepository;

import java.util.Date;
import java.util.List;

@DataJpaTest
public class ProductRepositoryMockTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @Sql(scripts = "/data.sql")
    public void findByCategory_thenReturnProductList() {

//        Category category = Category.builder().name("test").build();
//         categoryRepository.save(category);
        Category category = categoryRepository.findById(1L);

        Product product = Product.builder()
                .name("computer")
                .category(category)
                .description("")
                .stock(2)
                .price(10.55)
                .status("Created")
                .createdAt(new Date())
                .build();

        productRepository.save(product);
        List<Category> categorias = categoryRepository.findAll();

        List<Product> productList = productRepository.findByCategory(product.getCategory());

        Assertions.assertThat(productList.size()).isEqualTo(3);
    }
}
