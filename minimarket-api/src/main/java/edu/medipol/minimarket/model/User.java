package edu.medipol.minimarket.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class User extends BaseEntity {
    private String username;
    private String password;
    private String email;
}
