package edu.medipol.minimarket.controller;

import edu.medipol.minimarket.dto.ProductDTO;
import edu.medipol.minimarket.model.Product;
import edu.medipol.minimarket.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService productService = new ProductService();
	@Operation(summary = "Ürün ekle")	  	
	@PostMapping
    public ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        Product saved = productService.save(product);
        return new ResponseEntity<>(convertToDTO(saved), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
        return ResponseEntity.ok(products);
    }
    
    @GetMapping("/filter/price/{minPrice}")
    public ResponseEntity<List<ProductDTO>> getProductsAbovePrice(@PathVariable double minPrice) {
        List<ProductDTO> products = productService.findAll()
                .stream()
                .filter(p -> p.getPrice() >= minPrice)
                .sorted(Comparator.comparing(Product::getPrice))
                .map(this::convertToDTO)
                .toList();
        return ResponseEntity.ok(products);
    }
    
    @GetMapping("/sort/{by}")
    public ResponseEntity<List<ProductDTO>> sortProducts(@PathVariable String by) {
        List<Product> products = productService.findAll();
        
        if ("name".equalsIgnoreCase(by)) {
            products.sort(Comparator.comparing(Product::getName));
        } else if ("price".equalsIgnoreCase(by)) {
            products.sort(Comparator.comparing(Product::getPrice));
        }
        
        return ResponseEntity.ok(products.stream()
                .map(this::convertToDTO)
                .toList());
    }
    
    private ProductDTO convertToDTO(Product product) {
        if (product == null) {
            return null;
        }
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStock()
        );
    }

    private Product convertToEntity(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }
        return Product.builder()
                .name(productDTO.name())
                .price(productDTO.price())
                .stock(productDTO.stock())
                .build();
    }
}