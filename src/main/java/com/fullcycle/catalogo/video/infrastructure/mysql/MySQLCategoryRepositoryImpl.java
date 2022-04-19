package com.fullcycle.catalogo.video.infrastructure.mysql;

import com.fullcycle.catalogo.video.domain.entity.Category;
import com.fullcycle.catalogo.video.domain.repository.ICategoryRepository;
import com.fullcycle.catalogo.video.infrastructure.data.SpringDataCategoryRepository;
import com.fullcycle.catalogo.video.infrastructure.persistence.CategoryPersistence;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class MySQLCategoryRepositoryImpl implements ICategoryRepository {
    
    private SpringDataCategoryRepository repository;
    
    @Override
    public List<Category> findAll() {
        return repository
            .findAll()
            .parallelStream()
            .map(CategoryPersistence::fromThis)
            .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public Category create(Category category) {
        final var entity = CategoryPersistence.from(category);
        return repository.save(entity).fromThis();
    }

    @Override
    public Optional<Category> findById(UUID id) {
        return repository.findById(id).map(CategoryPersistence::fromThis);
    }

    @Override
    public void remove(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public void update(Category category) {
        final var entity = CategoryPersistence.from(category);
        repository.save(entity);
    }
}
