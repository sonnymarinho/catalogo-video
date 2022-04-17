package com.fullcycle.catalogo.video.domain.repository;

import com.fullcycle.catalogo.video.domain.entity.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class CategoryRepository implements ICategoryRepository {
    @Override
    public List<Category> findAll() {
        return null;
    }

    @Override
    public Category create(Category category) {
        return null;
    }

    @Override
    public Optional<Category> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public void remove(UUID id) {

    }

    @Override
    public void update(Category category) {

    }
}
