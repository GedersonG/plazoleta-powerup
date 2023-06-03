package com.pragma.plazoletaservice.infrastructure.input.rest;

import com.pragma.plazoletaservice.application.dto.request.DishRequestDto;
import com.pragma.plazoletaservice.application.dto.request.UpdateDishRequestDto;
import com.pragma.plazoletaservice.application.dto.response.DishResponseDto;
import com.pragma.plazoletaservice.application.handler.IDishHandler;
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
@RequiredArgsConstructor
@RequestMapping("/api/v1/dish")
public class DishRestController {
    
    private final IDishHandler dishHandler;

    @Operation(summary = "Add a new dish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dish created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Dish already exists", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Void> saveDish(@Valid @RequestBody DishRequestDto dishRequestDto) throws IllegalAccessException {
        dishHandler.saveDish(dishRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Get all categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All categoriess returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = DishResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<DishResponseDto>> getAllDishs() {
        return ResponseEntity.ok(dishHandler.getAllDishs());
    }

    @Operation(summary = "Get dish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dish returned",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DishResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<DishResponseDto> getDishById(@PathVariable Long id) {
        return ResponseEntity.ok(dishHandler.getDishById(id));
    }

    @Operation(summary = "Delete dish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dish deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Bad id dish", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDishById(@PathVariable Long id) {
        dishHandler.deleteDishById(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Update dish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dish updated", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad updated", content = @Content),
            @ApiResponse(responseCode = "404", description = "Bad id dish", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDishById(
            @PathVariable Long id,
            @Valid @RequestBody UpdateDishRequestDto requestDto
    ) throws IllegalAccessException {
        dishHandler.updateDishById(id, requestDto);
        return ResponseEntity.ok().build();
    }
}