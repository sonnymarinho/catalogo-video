package com.fullcycle.catalogo.video.domain.repository;

import com.fullcycle.catalogo.video.domain.entity.Category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ICategoryRepository {
    List<Category> findAll();

    Category create(Category category);

    Optional<Category> findById(UUID id);

    void remove(UUID id);

    void update(Category category);

}
