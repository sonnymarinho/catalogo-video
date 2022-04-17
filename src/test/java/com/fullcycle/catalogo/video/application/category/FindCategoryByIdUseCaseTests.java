package com.fullcycle.catalogo.video.application.category;

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

import static org.assertj.core.api.Assertions.assertThat;
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
}
