package com.fullcycle.catalogo.video.domain.application.usecase.category.remove;

import java.util.UUID;

public interface IRemoveCategoryUseCase {
    
    public void execute(UUID id);
}
