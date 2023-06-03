package com.pragma.plazoletaservice.infrastructure.input.rest;

import com.pragma.plazoletaservice.application.dto.request.CategoryRequestDto;
import com.pragma.plazoletaservice.application.dto.request.UpdateCategoryRequestDto;
import com.pragma.plazoletaservice.application.dto.response.CategoryResponseDto;
import com.pragma.plazoletaservice.application.handler.ICategoryHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryRestController {

    private final ICategoryHandler categoryHandler;

    @Operation(summary = "Add a new category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Category already exists", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Void> saveCategory(@Valid @RequestBody CategoryRequestDto categoryRequestDto) {
        categoryHandler.saveCategory(categoryRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Get all categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All categoriess returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CategoryResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getAllCategorys() {
        return ResponseEntity.ok(categoryHandler.getAllCategorys());
    }

    @Operation(summary = "Get category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category returned",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoryResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryHandler.getCategoryById(id));
    }

    @Operation(summary = "Delete category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Bad id category", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Long id) {
        categoryHandler.deleteCategoryById(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Update category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category updated", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad updated", content = @Content),
            @ApiResponse(responseCode = "404", description = "Bad id category", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCategoryById(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCategoryRequestDto requestDto
    ) {
        categoryHandler.updateCategoryById(id, requestDto);
        return ResponseEntity.ok().build();
    }
}
