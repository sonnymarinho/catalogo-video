package com.fullcycle.catalogo.video.infrastructure.data;

import com.fullcycle.catalogo.video.infrastructure.persistence.CategoryPersistence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataCategoryRepository extends JpaRepository<CategoryPersistence, UUID> {
}
