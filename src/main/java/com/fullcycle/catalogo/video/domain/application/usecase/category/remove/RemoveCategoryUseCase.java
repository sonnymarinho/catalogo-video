package com.fullcycle.catalogo.video.domain.application.usecase.category.remove;

import com.fullcycle.catalogo.video.domain.repository.ICategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class RemoveCategoryUseCase implements IRemoveCategoryUseCase {
    
    private ICategoryRepository repository;

    @Override
    public void execute(UUID id) {
        repository.remove(id);
    }
}
