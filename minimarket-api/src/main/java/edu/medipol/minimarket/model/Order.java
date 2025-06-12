package edu.medipol.minimarket.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Order extends BaseEntity {
    @ManyToOne
    private User user;
    
    @OneToMany
    private List<OrderItem> items;
    private Double totalPrice;
}
