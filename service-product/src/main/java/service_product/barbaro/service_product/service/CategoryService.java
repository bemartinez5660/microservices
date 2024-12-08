package service_product.barbaro.service_product.service;

import org.springframework.stereotype.Service;
import service_product.barbaro.service_product.entity.Category;
import service_product.barbaro.service_product.repository.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void addCategory() {
        Category category = Category.builder()
                .name("prueba")
                .build();
        categoryRepository.save(category);

        Category category2 = Category.builder()
                .name("prueba")
                .build();

        categoryRepository.save(category2);
    }
}
