package service_product.barbaro.service_product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import service_product.barbaro.service_product.service.CategoryService;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add-category")
    public void addCategory(){
        this.categoryService.addCategory();
    }
}
