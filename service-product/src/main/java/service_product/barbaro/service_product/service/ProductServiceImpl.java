package service_product.barbaro.service_product.service;

import org.springframework.stereotype.Service;
import service_product.barbaro.service_product.entity.Category;
import service_product.barbaro.service_product.entity.Product;
import service_product.barbaro.service_product.repository.ProductRepository;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {

        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(Product product) {
        product.setStatus("CREATED");
        product.setCreatedAt(new Date());
        productRepository.save(product);
        return product;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product productDB = productRepository.findById(id).orElse(null);
        if (productDB == null) {
            return null;
        }
        product.setId(productDB.getId());
        product.setStatus("UPDATED");
        product.setCreatedAt(productDB.getCreatedAt());
        productRepository.save(product);
        return product;
    }

    @Override
    public Product deleteProduct(Long id) {
        Product productDB = productRepository.findById(id).orElse(null);
        if (productDB == null) {
            return null;
        }
        productDB.setStatus("DELETED");
        return productRepository.save(productDB);
    }

    @Override
    public List<Product> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    @Override
    public Product updateStock(Long id, Double quantity) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return null;
        }
        product.setStock(product.getStock() + quantity);
        return productRepository.save(product);
    }
}
