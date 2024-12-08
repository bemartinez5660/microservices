package service_product.barbaro.service_product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import service_product.barbaro.service_product.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    public Category findById(long id);
}
