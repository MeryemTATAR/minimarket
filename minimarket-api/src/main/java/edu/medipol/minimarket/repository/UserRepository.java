package edu.medipol.minimarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.medipol.minimarket.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
