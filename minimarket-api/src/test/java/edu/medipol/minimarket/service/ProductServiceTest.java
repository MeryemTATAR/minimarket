package edu.medipol.minimarket.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import edu.medipol.minimarket.model.Product;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @Test
    void whenSaveProduct_thenProductShouldBeFound() {
        Product product = new Product();
        productService.save(product);
        
        assertThat(productService.findProductByName("Test")).isNotNull();
    }
}