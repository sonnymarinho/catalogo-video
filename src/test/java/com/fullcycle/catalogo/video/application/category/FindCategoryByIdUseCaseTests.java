package com.fullcycle.catalogo.video.application.category;

import com.fullcycle.catalogo.video.domain.application.exception.NotFoundException;
import com.fullcycle.catalogo.video.domain.application.usecase.category.find.by.id.FindCategoryByIdUseCase;
import com.fullcycle.catalogo.video.domain.entity.Category;
import com.fullcycle.catalogo.video.domain.repository.ICategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class FindCategoryByIdUseCaseTests {

    @InjectMocks
    private FindCategoryByIdUseCase useCase;
    
    @Mock
    private ICategoryRepository repository;
    
    @BeforeEach
    public void setUp() {
        useCase = new FindCategoryByIdUseCase(repository);
    }
    
    @Test
    @DisplayName("it should be able to return a category by id")
    public void shouldBeAbleToReturnCategoryById() {
        var mockCategory = new Category("name", "description");
        var optionalCategory = Optional.of(mockCategory);
        
        when(repository.findById(mockCategory.getId())).thenReturn(optionalCategory);

        var result = useCase.execute(mockCategory.getId());
        
        assertThat(result).isNotNull();
        
        assertThat(result.getId()).isEqualTo(mockCategory.getId());
        assertThat(result.getName()).isEqualTo(mockCategory.getName());
        assertThat(result.getDescription()).isEqualTo(mockCategory.getDescription()); 
        
        verify(repository, times(1)).findById(mockCategory.getId());
    }
    
    @Test
    @DisplayName("it should not be able to return a category by id when it does not exist")
    public void shouldNotBeAbleToReturnCategoryByIdIfIdDoesNotExists() {
        var mockCategory = new Category("name", "description");
        var randomUUID = UUID.randomUUID();
        
        when(repository.findById(mockCategory.getId())).thenReturn(Optional.of(mockCategory));
        assertDoesNotThrow(() -> useCase.execute(mockCategory.getId()));
        verify(repository, times(1)).findById(mockCategory.getId());

        when(repository.findById(any(UUID.class))).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> useCase.execute(randomUUID));
        verify(repository, times(1)).findById(randomUUID);
    }
}
