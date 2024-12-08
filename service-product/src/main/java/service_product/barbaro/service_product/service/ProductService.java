package service_product.barbaro.service_product.service;

import org.springframework.stereotype.Service;
import service_product.barbaro.service_product.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> findAllProducts();
    public Product getProduct(Long id);
    public Product createProduct(Product product);
    public Product updateProduct(Long id, Product product);
    public Product deleteProduct(Long id);
    public List<Product> getProductsByCategory(Long category);
    public Product updateStock(Long id, Double quantity);
}
