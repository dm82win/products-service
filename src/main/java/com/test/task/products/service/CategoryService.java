package com.test.task.products.service;

import com.test.task.products.model.Category;
import java.util.Optional;

public interface CategoryService {

    Optional<Category> getByName(String category);

    Category createCategory(String category);
}
