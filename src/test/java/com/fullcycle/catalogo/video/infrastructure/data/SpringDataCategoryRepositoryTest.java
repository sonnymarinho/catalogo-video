package com.fullcycle.catalogo.video.infrastructure.data;

import com.fullcycle.catalogo.video.domain.entity.Category;
import com.fullcycle.catalogo.video.infrastructure.persistence.CategoryPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class SpringDataCategoryRepositoryTest {
    
    @Autowired
    private SpringDataCategoryRepository repository;
   
    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }
    
    @Test
    @DisplayName("it should be able to save a category")
    void save() {
        var domainEntity = new Category("Action", "Action movies");
        var input = CategoryPersistence.from(domainEntity);
        
        CategoryPersistence actual = repository.save(input);
        
        assertThat(actual.getId()).isNotNull();
        assertThat(actual.getName()).isEqualTo(input.getName());
        assertThat(actual.getDescription()).isEqualTo(input.getDescription());
        assertTrue(actual.getIsActive());
    }
    
    @Test
    @DisplayName("it should be able to update a category")
    void update() {
        var domainEntity = new Category("Action", "Action movies");
        var input = CategoryPersistence.from(domainEntity);
        
        CategoryPersistence actual = repository.save(input);
        
        assertThat(actual.getId()).isNotNull();
        assertThat(actual.getName()).isEqualTo(input.getName());
        assertThat(actual.getDescription()).isEqualTo(input.getDescription());
        assertTrue(actual.getIsActive());

        domainEntity.update("Horror", "Horror movies description", false);
        
        var update = CategoryPersistence.from(domainEntity);
        
        var updateResult = repository.save(update);
        
        assertThat(updateResult.getId()).isNotNull();
        assertThat(updateResult.getName()).isEqualTo("Horror");
        assertThat(updateResult.getDescription()).isEqualTo("Horror movies description");
        assertFalse(updateResult.getIsActive());
    }
    
    @Test
    @DisplayName("it should be able to find all the categories")
    void saveAll() {
        
        var input1 = CategoryPersistence.from(new Category("Action", "Action movies"));
        var input2 = CategoryPersistence.from(new Category("Adventure", "Adventure movies"));
        var input3 = CategoryPersistence.from(new Category("Comedy", "Comedy movies"));
        
        repository.saveAll(List.of(input1, input2, input3));
        
        var actual = repository.findAll();
        
        assertThat(actual).hasSize(3);
    }
    
    @Test
    @DisplayName("it should be able to find a category by id")
    void findById() {
        var domainEntity = new Category("Action", "Action movies");
        var input = CategoryPersistence.from(domainEntity);
        
        CategoryPersistence actual = repository.save(input);
        
        assertThat(actual.getId()).isNotNull();
        assertThat(actual.getName()).isEqualTo(input.getName());
        assertThat(actual.getDescription()).isEqualTo(input.getDescription());
        assertTrue(actual.getIsActive());
        
        var findById = repository.findById(actual.getId());
        
        assertThat(findById.get()).isEqualTo(actual);
        assertTrue(findById.isPresent());
    }
    
    @Test
    @DisplayName("it should be able to delete a category")
    void delete() {
        var domainEntity = new Category("Action", "Action movies");
        var input = CategoryPersistence.from(domainEntity);
        
        CategoryPersistence actual = repository.save(input);
        
        assertThat(actual.getId()).isNotNull();
        assertThat(actual.getName()).isEqualTo(input.getName());
        assertThat(actual.getDescription()).isEqualTo(input.getDescription());
        assertTrue(actual.getIsActive());
        
        repository.delete(actual);
        
        var findById = repository.findById(actual.getId());
        
        assertFalse(findById.isPresent());
    }
}