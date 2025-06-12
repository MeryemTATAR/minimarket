package edu.medipol.minimarket.repository;

import edu.medipol.minimarket.model.Product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	Optional<Product> findByNameIgnoreCase(String name);
    List<Product> findByStockLessThanEqual(int stock);
}
