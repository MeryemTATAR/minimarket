package edu.medipol.minimarket.service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SearchService<T> {
    public List<T> search(List<T> items, Predicate<T> predicate) {
        return items.stream()
               .filter(predicate)
               .collect(Collectors.toList());
    }
}
