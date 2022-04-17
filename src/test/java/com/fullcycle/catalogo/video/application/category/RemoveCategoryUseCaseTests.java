package com.fullcycle.catalogo.video.application.category;


import com.fullcycle.catalogo.video.domain.application.usecase.category.find.by.id.FindCategoryByIdUseCase;
import com.fullcycle.catalogo.video.domain.application.usecase.category.remove.RemoveCategoryUseCase;
import com.fullcycle.catalogo.video.domain.repository.ICategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class RemoveCategoryUseCaseTests {

    @InjectMocks
    private RemoveCategoryUseCase useCase;

    @Mock
    private ICategoryRepository repository;

    @BeforeEach
    public void setUp() {
        useCase = new RemoveCategoryUseCase(repository);
    }

    @Test
    @DisplayName("it should be able to remove a category")
    public void remove() {
        var uuid = UUID.randomUUID();
        
        doNothing().when(repository).remove(uuid);
        
        assertDoesNotThrow(() -> useCase.execute(uuid));
        verify(repository, times(1)).remove(uuid);
    }
}
