package com.test.task.products.service.impl;

import com.test.task.products.model.Category;
import com.test.task.products.repository.CategoryRepository;
import com.test.task.products.service.CategoryService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Optional<Category> getByName(String category) {
        return categoryRepository.findByName(category);
    }

    @Override
    public Category createCategory(String category) {
        return categoryRepository.save(new Category(category));
    }
}
