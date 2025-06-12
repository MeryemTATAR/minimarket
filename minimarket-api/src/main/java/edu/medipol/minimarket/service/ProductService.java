package edu.medipol.minimarket.service;

import edu.medipol.minimarket.CrudService;
import edu.medipol.minimarket.model.Product;
import edu.medipol.minimarket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.function.Predicate;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class ProductService implements CrudService<Product, Long> {

    private ProductRepository productRepository = null;
    private final Lock lock = new ReentrantLock();

    @Autowired
    public ProductService() {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
    
    public synchronized void updateStock(Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
        product.setStock(product.getStock() + quantity);
        productRepository.save(product);
    }
    
    @Async
    public CompletableFuture<String> generateProductReport() {
        try {
            Thread.sleep(2000);
            return CompletableFuture.completedFuture("Report generated!");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return CompletableFuture.failedFuture(e);
        }
    }
    
    public Optional<Product> findProductByName(String name) {
        return productRepository.findByNameIgnoreCase(name);
    }
    
    public List<Product> searchProducts(Predicate<Product> predicate) {
        return productRepository.findAll()
                .stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
    
    @Async("threadPoolTaskExecutor")
    public CompletableFuture<Void> safeStockUpdate(Long productId, int quantity) {
        lock.lock();
        try {
            updateStock(productId, quantity);
            Thread.sleep(1000);
            return CompletableFuture.completedFuture(null);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return CompletableFuture.failedFuture(e);
        } finally {
            lock.unlock();
        }
    }
    
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> findOutOfStockProducts() {
        return productRepository.findByStockLessThanEqual(0);
    }
}