package edu.medipol.minimarket;

import java.util.List;

public interface CrudService<T, ID> {
    List<T> findAll();
    T save(T entity);
    void deleteById(ID id);
}
