package service_product.barbaro.service_product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import service_product.barbaro.service_product.entity.Category;
import service_product.barbaro.service_product.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    public List<Product> findByCategoryId(Long categoryId);
}
