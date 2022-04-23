package com.fullcycle.catalogo.video.api.category;

import com.fullcycle.catalogo.video.application.usecase.category.common.CategoryOutputData;
import com.fullcycle.catalogo.video.application.usecase.category.common.UpdateCategoryInputData;
import com.fullcycle.catalogo.video.application.usecase.category.create.CreateCategoryInputData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("categories")
@Api("Categories")
public interface ICategoryEndpoint {

    @PostMapping
    @ResponseStatus(code = CREATED)
    @ApiOperation("Create a new category")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Category created"),
        @ApiResponse(code = 400, message = "Invalid input data"),
        @ApiResponse(code = 409, message = "Category already exists"),
        @ApiResponse(code = 500, message = "Internal server error"),

    })
    public CategoryOutputData create(@RequestBody CreateCategoryInputData inputData);

    @GetMapping
    @ResponseStatus(code = OK)
    @ApiOperation("Find all categories")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Categories found"),
        @ApiResponse(code = 500, message = "Internal server error"),
    })
    public List<CategoryOutputData> findAll();

    @GetMapping("{id}")
    @ResponseStatus(code = OK)
    @ApiOperation("Find a category by id")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Category found"),
        @ApiResponse(code = 404, message = "Category not found"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    public CategoryOutputData findById(@PathVariable("id") UUID id);

    @PutMapping("{id}")
    @ResponseStatus(code = NO_CONTENT)
    @ApiOperation("Update a category")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Category updated"),
        @ApiResponse(code = 400, message = "Invalid input data"),
        @ApiResponse(code = 404, message = "Category not found"),
        @ApiResponse(code = 409, message = "Category already exists"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    public void update(@PathVariable("id") UUID id, @RequestBody UpdateCategoryInputData inputData);

    @DeleteMapping("{id}")
    @ResponseStatus(code = NO_CONTENT)
    @ApiOperation("Delete a category")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Category deleted"),
        @ApiResponse(code = 404, message = "Category not found"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    public void delete(@PathVariable("id") UUID id);
}
