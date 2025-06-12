package edu.medipol.minimarket.service;

import edu.medipol.minimarket.CrudService;
import edu.medipol.minimarket.model.User;
import edu.medipol.minimarket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements CrudService<User, Long> {

    @Autowired
    private UserRepository repository;
    
    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User save(User entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    
    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }
}