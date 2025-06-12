package edu.medipol.minimarket.model;

import edu.medipol.minimarket.annotation.Loggable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Loggable
    private Long id;
    private String name;
    private double price;
    private int stock;
    
    public double getPrice() {
        return this.price;
    }
    
    public String getName() {
        return this.name;
    }
     

    public Product() {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

	public int getStock() {
		return this.stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public Long getId() {
	        return id;
    }

	public void setId(Long id) {
	        this.id = id;
	}
	
	public static ProductBuilder builder() {
        return new ProductBuilder();
    }

    public static class ProductBuilder {
        private Product product = new Product();

        public ProductBuilder name(String name) {
            product.setName(name);
            return this;
        }
        
        public ProductBuilder price(double price) {
            product.setPrice(price);
            return this;
        }

        public ProductBuilder stock(int stock) {
            product.setStock(stock);
            return this;
        }

        public Product build() {
            return product;
        }
    }

	public void setPrice(double price2) {
		// TODO Auto-generated method stub
		
	}

	public void setName(String name2) {
		// TODO Auto-generated method stub
		
	}

	
}
