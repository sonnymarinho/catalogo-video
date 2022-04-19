package com.fullcycle.catalogo.video.infrastructure.mysql;

import com.fullcycle.catalogo.video.domain.entity.Category;
import com.fullcycle.catalogo.video.infrastructure.data.SpringDataCategoryRepository;
import com.fullcycle.catalogo.video.infrastructure.persistence.CategoryPersistence;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class MySQLCategoryRepositoryImplTest {
     
    @InjectMocks
    private MySQLCategoryRepositoryImpl repository;
    
    @Mock
    private SpringDataCategoryRepository springDataRepository;
    
    @Test
    @DisplayName("it should be able to save and return a category")
    public void saveCategory() {
        Category expected = new Category("Name","Description");
        
        Category input = new Category("Name","Description");
        
        doReturn(CategoryPersistence.from(expected)).when(springDataRepository).save(any(CategoryPersistence.class));
        
        var result = repository.create(input);
        
        assertThat(result.getName()).isEqualTo(expected.getName());
        assertThat(result.getDescription()).isEqualTo(expected.getDescription());
        assertThat(result.getIsActive()).isEqualTo(expected.getIsActive());
        
        assertThat(result).hasFieldOrPropertyWithValue("name", "Name");
        assertThat(result).hasFieldOrPropertyWithValue("description", "Description");
        assertThat(result).hasFieldOrPropertyWithValue("isActive", true);
    }
    
    
    @Test
    @DisplayName("it should be able to find all categories")
    public void findAllCategories() {
        Category input3 = new Category("Name 1","Description 1");
        Category input1 = new Category("Name 2","Description 2");
        Category input2 = new Category("Name 3","Description 3");

        List<CategoryPersistence> expected = List.of(
                CategoryPersistence.from(input1),
                CategoryPersistence.from(input2),
                CategoryPersistence.from(input3)
        );
        
        
        doReturn(expected).when(springDataRepository).findAll();
        
        var result = repository.findAll();
        
        
        assertThat(result).isNotNull();
        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(3);       
    }

    @Test
    @DisplayName("it should be able to find a category by id")
    public void findById() {
        Category entity = new Category("Name","Description");

        CategoryPersistence input = CategoryPersistence.from(entity);

        doReturn(Optional.of(input)).when(springDataRepository).findById(entity.getId());
        
        
        Optional<Category> actual = repository.findById(entity.getId());
        
        assertThat(actual).isNotNull();
        assertThat(actual).isNotEmpty();
        assertThat(actual.isPresent()).isTrue();
        
        assertThat(actual.get()).hasFieldOrPropertyWithValue("id", entity.getId());
        assertThat(actual.get()).hasFieldOrPropertyWithValue("name", "Name");
        assertThat(actual.get()).hasFieldOrPropertyWithValue("description", "Description");
        assertThat(actual.get()).hasFieldOrPropertyWithValue("isActive", true);
    }
    
    @Test
    @DisplayName("it should be able to update a category")
    public void updateCategory() {
        Category expected = new Category("Name","Description");

        Category input = new Category("Name","Description");

        doReturn(CategoryPersistence.from(expected)).when(springDataRepository).save(CategoryPersistence.from(input));
        
        repository.update(input);
        
        verify(springDataRepository, times(1)).save(CategoryPersistence.from(input));
    }
    
    @Test
    @DisplayName("it should be able to delete a category")
    public void deleteCategory() {
        Category entity = new Category("Name","Description");

        doReturn(CategoryPersistence.from(entity)).when(springDataRepository).save(any(CategoryPersistence.class));

        var toUpdate = repository.create(entity);

        toUpdate.update("Name 2", entity.getDescription(), Boolean.FALSE);
        
        assertThat(toUpdate.getName()).isEqualTo("Name 2");
        assertThat(toUpdate.getDescription()).isEqualTo("Description");
        assertThat(toUpdate.getIsActive()).isEqualTo(false);

        verify(springDataRepository, atMost(2)).save(CategoryPersistence.from(toUpdate));
    }
}