package edu.medipol.minimarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.medipol.minimarket.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {}