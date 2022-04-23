package com.fullcycle.catalogo.video.api.category;

import com.fullcycle.catalogo.video.application.usecase.category.common.CategoryOutputData;
import com.fullcycle.catalogo.video.application.usecase.category.common.UpdateCategoryInputData;
import com.fullcycle.catalogo.video.application.usecase.category.create.CreateCategoryInputData;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("categories")
public interface ICategoryEndpoint {
    
    @PostMapping
    @ResponseStatus(code = CREATED)
    public CategoryOutputData create(@RequestBody CreateCategoryInputData inputData);
    
    @GetMapping
    @ResponseStatus(code = OK)
    public List<CategoryOutputData> findAll();
    
    @GetMapping("{id}")
    @ResponseStatus(code = OK)
    public CategoryOutputData findById(@PathVariable("id") UUID id);
    
    @PutMapping("{id}")
    @ResponseStatus(code = NO_CONTENT)
    public void update(@PathVariable("id") UUID id, @RequestBody UpdateCategoryInputData inputData);
    
    @DeleteMapping("{id}")
    @ResponseStatus(code = NO_CONTENT)
    public void delete(@PathVariable("id") UUID id);
}
