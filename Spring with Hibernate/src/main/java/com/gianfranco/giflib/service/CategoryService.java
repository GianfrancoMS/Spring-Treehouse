package com.gianfranco.giflib.service;

import com.gianfranco.giflib.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category findById(Long id);

    void save(Category category);

    void delete(Category category);
}
